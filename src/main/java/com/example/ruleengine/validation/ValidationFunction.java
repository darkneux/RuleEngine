package com.example.ruleengine.validation;

@FunctionalInterface
public interface ValidationFunction {
    void validate(Object value);
}
