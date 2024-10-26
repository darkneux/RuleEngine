package com.example.ruleengine.service;

import com.example.ruleengine.dto.RuleDTO;
import com.example.ruleengine.entity.RuleEntity;
import com.example.ruleengine.mapper.RuleMapper;
import com.example.ruleengine.model.ASTNode;
import com.example.ruleengine.model.LogicalOperator;
import com.example.ruleengine.model.OperandNode;
import com.example.ruleengine.model.OperatorNode;
import com.example.ruleengine.parser.DefaultRuleParser;
import com.example.ruleengine.parser.RuleParser;
import com.example.ruleengine.repository.RuleRepository;
import com.example.ruleengine.serializer.JsonSerializer;
import com.example.ruleengine.serializer.Serializer;
import com.example.ruleengine.serializer.StringSerializer;
import com.example.ruleengine.validation.AttributeCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private AttributeCatalog attributeCatalog;

    private final Serializer jsonSerializer;
    private final Serializer stringSerializer;
    private final ASTConverter astConverter;

    public RuleService(AttributeCatalog attributeCatalog) {
        this.attributeCatalog = attributeCatalog;
        this.jsonSerializer = new JsonSerializer();
        this.stringSerializer = new StringSerializer();
        this.astConverter = new ASTConverter(jsonSerializer, stringSerializer);
    }

    public void createRule(RuleDTO ruleDTO) {
        try {
            RuleParser parser = new DefaultRuleParser(attributeCatalog);
            ASTNode astNode = parser.createRuleAST(ruleDTO.getRule());

            String serializedAst = astConverter.serialize(astNode, ruleDTO.getSerializationType());

            RuleEntity ruleEntity = new RuleEntity();
            ruleEntity.setName(ruleDTO.getName());
            ruleEntity.setAstData(serializedAst);
            ruleEntity.setSerializationType(ruleDTO.getSerializationType());

            ruleRepository.save(ruleEntity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save rule: " + e.getMessage(), e);
        }
    }

    public RuleDTO getRuleByName(String name) {
        RuleEntity ruleEntity = ruleRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Rule not found: " + name));
        return RuleMapper.toDTO(ruleEntity); // Convert to DTO using the mapper
    }

    public boolean evaluateRule(String name, Map<String, Object> data) {
        try {
            RuleEntity ruleEntity = ruleRepository.findById(name)
                    .orElseThrow(() -> new RuntimeException("Rule not found: " + name));
            System.out.println("::::::::::::::  "+ruleEntity);
            System.out.println("::::::::::::::  "+data);

            System.out.println("here-");

            ASTNode astNode = astConverter.deserialize(ruleEntity.getAstData(), ruleEntity.getSerializationType());
            System.out.println("here--");

            return evaluateASTNode(astNode, data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to evaluate rule: " + e.getMessage(), e);
        }
    }

    private boolean evaluateASTNode(ASTNode astNode, Map<String, Object> data) {
        if (astNode.isOperand()) {
            OperandNode operandNode = (OperandNode) astNode;
            String attribute = operandNode.getAttribute();
            String operator = operandNode.getOperator();
            Object value = operandNode.getValue();

            System.out.println(attribute+"   "+operator+"   "+value);


            Object actualValue = data.get(attribute);

            switch (operator) {
                case ">":
                case "gt":
                    return compareValues(actualValue, value) > 0;
                case "<":
                case "lt":
                    return compareValues(actualValue, value) < 0;
                case "=":
                case "eq":
                    return actualValue != null && actualValue.equals(value);
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }
        } else if (astNode.isLogicalOperator()) {
            OperatorNode operatorNode = (OperatorNode) astNode;
            boolean leftResult = evaluateASTNode(operatorNode.getLeftChild(), data);
            boolean rightResult = evaluateASTNode(operatorNode.getRightChild(), data);

            return LogicalOperator.apply(operatorNode.getOperator(), leftResult, rightResult);
        }

        throw new IllegalArgumentException("Invalid ASTNode type.");
    }

    private int compareValues(Object actualValue, Object expectedValue) {
        if (actualValue instanceof Comparable && expectedValue instanceof Comparable) {
            return ((Comparable<Object>) actualValue).compareTo(expectedValue);
        }
        throw new IllegalArgumentException("Incompatible types for comparison: "
                + actualValue.getClass() + " and " + expectedValue.getClass());
    }

}
