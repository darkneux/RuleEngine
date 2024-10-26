package com.example.ruleengine.dto;

import com.example.ruleengine.serializer.SerializationType;
import lombok.Data;

@Data
public class RuleDTO {
    private String name;
    private String rule;
    private SerializationType serializationType;


}
