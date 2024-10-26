package com.example.ruleengine.serializer;

import com.example.ruleengine.model.ASTNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer implements Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String serialize(ASTNode astNode) throws Exception {
        return objectMapper.writeValueAsString(astNode);
    }

    @Override
    public ASTNode deserialize(String data) throws Exception {
        return objectMapper.readValue(data, ASTNode.class);
    }
}
