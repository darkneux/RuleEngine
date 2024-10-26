package com.example.ruleengine.serializer;

import com.example.ruleengine.model.ASTNode;
import com.example.ruleengine.model.OperatorNode;
import com.example.ruleengine.model.ValueNode;
import java.util.Stack;

public class StringSerializer implements Serializer {

    @Override
    public String serialize(ASTNode astNode) throws Exception {
        return astNode.toString();
    }

    @Override
    public ASTNode deserialize(String data) throws Exception {
        return buildASTFromRule(data);
    }

    private ASTNode buildASTFromRule(String rule) {
        String[] tokens = rule.split(" ");

        Stack<ASTNode> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (isOperator(token)) {

                ASTNode rightOperand = stack.pop();
                ASTNode leftOperand = stack.pop();


                OperatorNode operatorNode = new OperatorNode(token, leftOperand, rightOperand);

                stack.push(operatorNode);
            } else if (isValue(token)) {

                ValueNode valueNode = new ValueNode(token);
                stack.push(valueNode);
            } else {

                ValueNode valueNode = new ValueNode(token);
                stack.push(valueNode);
            }
        }

        return stack.isEmpty() ? null : stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("AND") || token.equals("OR") || token.equals(">") || token.equals("<");
    }

    private boolean isValue(String token) {
        return token.matches("\\d+");
    }
}
