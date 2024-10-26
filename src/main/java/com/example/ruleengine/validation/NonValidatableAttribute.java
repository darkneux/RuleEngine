package com.example.ruleengine.validation;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class NonValidatableAttribute extends Attribute {

    public NonValidatableAttribute(String name, Class<?> type) {
        super(name, type);
    }

    @Override
    public void validate(Object value) {
    }
}
