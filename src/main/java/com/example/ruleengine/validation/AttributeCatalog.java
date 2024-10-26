package com.example.ruleengine.validation;

import java.util.HashMap;
import java.util.Map;

public class AttributeCatalog {

    private final Map<String, Attribute> attributes = new HashMap<>();

    public void addAttribute(Attribute attribute) {
        if (attributes.containsKey(attribute.getName())) {
            throw new IllegalArgumentException("Attribute '" + attribute.getName() + "' already exists.");
        }
        attributes.put(attribute.getName(), attribute);
    }

    public void validate(String name, Object value) {
        Attribute attribute = attributes.get(name);
        if (attribute == null) {
            throw new IllegalArgumentException("Attribute '" + name + "' is not registered.");
        }
        attribute.validate(value);
    }

    public boolean containsAttribute(String name) {
        return attributes.containsKey(name);
    }
}
