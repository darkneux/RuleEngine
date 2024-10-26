package com.example.ruleengine.parser;

import com.example.ruleengine.model.ASTNode;
import com.example.ruleengine.model.OperandNode;
import com.example.ruleengine.model.OperatorNode;
import com.example.ruleengine.validation.AttributeCatalog;

public class DefaultRuleParser implements RuleParser {

    private final AttributeCatalog catalog;

    public DefaultRuleParser(AttributeCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public ASTNode createRuleAST(String rule) {
        rule = stripOuterParentheses(rule);
        return parseExpression(rule);
    }

    private String stripOuterParentheses(String rule) {
        rule = rule.trim();
        if (rule.startsWith("(") && rule.endsWith(")")) {
            int balance = 0;
            for (int i = 0; i < rule.length(); i++) {
                char c = rule.charAt(i);
                if (c == '(') balance++;
                else if (c == ')') balance--;
                if (balance == 0 && i < rule.length() - 1) {
                    return rule;
                }
            }
            return rule.substring(1, rule.length() - 1).trim();
        }
        return rule;
    }

    private ASTNode parseExpression(String rule) {
        int orIndex = findOperator(rule, "OR");
        if (orIndex != -1) {
            ASTNode left = parseExpression(rule.substring(0, orIndex).trim());
            ASTNode right = parseExpression(rule.substring(orIndex + 2).trim());
            return new OperatorNode("OR", left, right);
        }

        int andIndex = findOperator(rule, "AND");
        if (andIndex != -1) {
            ASTNode left = parseExpression(rule.substring(0, andIndex).trim());
            ASTNode right = parseExpression(rule.substring(andIndex + 3).trim());
            return new OperatorNode("AND", left, right);
        }

        if (rule.startsWith("(") && rule.endsWith(")")) {
            return createRuleAST(rule);
        }

        return validateAndReturnOperand(rule.trim());
    }

    private ASTNode validateAndReturnOperand(String operand) {
        String[] parts = operand.trim().split("\\s+");

        for(String i : parts) {
            System.out.println(i);
        }

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid operand format: " + operand);
        }

        String attribute = parts[0];
        String operator = parts[1];
        String value = operand.substring(attribute.length() + operator.length() + 2).trim();

        if (catalog != null && !catalog.containsAttribute(attribute)) {
            throw new IllegalArgumentException("Attribute '" + attribute + "' is not registered.");
        }


        return new OperandNode(attribute, operator, value);
    }

    private int findOperator(String rule, String operator) {
        int balance = 0;
        for (int i = 0; i < rule.length() - operator.length() + 1; i++) {
            char c = rule.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance == 0 && rule.startsWith(operator, i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ASTNode combineRulesAST(ASTNode rule1AST, ASTNode rule2AST, String operator) {
        return new OperatorNode(operator, rule1AST, rule2AST);
    }

    @Override
    public ASTNode parseFromString(String serializedRule) {
        return createRuleAST(serializedRule);
    }
}
