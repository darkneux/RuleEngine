package com.example.ruleengine.model;

public class ValueNode extends ASTNode {
    private String value;

    public ValueNode(String value) {
        this.value = value;
    }

    @Override
    public boolean isOperand() {
        return true;
    }

    @Override
    public boolean isLogicalOperator() {
        return false;
    }

    @Override
    public String toString() {
        return value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
