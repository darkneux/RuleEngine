package com.example.ruleengine.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OperandNode extends ASTNode {

    private String attribute;
    private String operator;
    private Object value;

    public OperandNode() {}

    public OperandNode(String attribute, String operator, Object value) {
        this.attribute = attribute;
        this.operator = operator;
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
    public String getAttribute() {
        return attribute;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", attribute, operator, value);
    }
}
