package com.example.ruleengine.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.Map;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "nodeType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OperatorNode.class, name = "OperatorNode"),
        @JsonSubTypes.Type(value = OperandNode.class, name = "OperandNode")
})
public abstract class ASTNode {

    public abstract boolean isOperand();
    public abstract boolean isLogicalOperator();

    public ASTNode getLeftChild() {
        return null;
    }

    public ASTNode getRightChild() {
        return null;  // Return null instead of throwing exception
    }

    public String getOperator() {
        return null;  // Default operator is null for non-operator nodes
    }

    public String getAttribute() {
        return null;  // Default attribute is null for non-operand nodes
    }

    public Object getValue() {
        return null;  // Default value is null for non-operand nodes
    }


    @Override
    public abstract String toString();  // Enforce toString in all subclasses
}

