package com.example.ruleengine.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
public class ValidatableAttribute extends Attribute {

    private ValidationFunction validationFunction;

    public ValidatableAttribute(String name, Class<?> type, ValidationFunction validationFunction) {
        super(name, type);
        this.validationFunction = validationFunction;
    }

    @Override
    public void validate(Object value) {
        if (validationFunction != null) {
            validationFunction.validate(value);
        }
    }
}
