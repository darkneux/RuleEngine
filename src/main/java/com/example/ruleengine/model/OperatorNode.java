package com.example.ruleengine.model;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
public class OperatorNode extends ASTNode {

    private String operator;  // e.g., "AND", "OR"
    private ASTNode left;
    private ASTNode right;

    public OperatorNode() {}

    public OperatorNode(String operator, ASTNode left, ASTNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isOperand() {
        return false;
    }

    @Override
    public boolean isLogicalOperator() {
        return true;
    }

    @Override
    public ASTNode getLeftChild() {
        return left;
    }

    @Override
    public ASTNode getRightChild() {
        return right;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %s)", operator, left.toString(), right.toString());
    }
}
