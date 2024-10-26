package com.example.ruleengine.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Attribute {
    protected String name;
    protected Class<?> type;

    public abstract void validate(Object value);
}
