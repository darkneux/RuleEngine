package com.example.ruleengine.model;

public enum LogicalOperator {
    AND,
    OR;

    public static boolean apply(String operator, boolean left, boolean right) {
        switch (operator) {
            case "AND":
                return left && right;
            case "OR":
                return left || right;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
