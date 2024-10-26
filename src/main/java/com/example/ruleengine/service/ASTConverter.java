package com.example.ruleengine.service;

import com.example.ruleengine.model.ASTNode;
import com.example.ruleengine.serializer.SerializationType;
import com.example.ruleengine.serializer.Serializer;

public class ASTConverter {

    private final Serializer jsonSerializer;
    private final Serializer stringSerializer;

    public ASTConverter(Serializer jsonSerializer, Serializer stringSerializer) {
        this.jsonSerializer = jsonSerializer;
        this.stringSerializer = stringSerializer;
    }

    public String serialize(ASTNode astNode, SerializationType type) throws Exception {
        switch (type) {
            case JSON:
                return jsonSerializer.serialize(astNode);
            case STRING:
                return stringSerializer.serialize(astNode);
            default:
                throw new IllegalArgumentException("Unsupported serialization type: " + type);
        }
    }

    public ASTNode deserialize(String data, SerializationType type) throws Exception {
        switch (type) {
            case JSON:
                return jsonSerializer.deserialize(data);
            case STRING:
                return stringSerializer.deserialize(data);
            default:
                throw new IllegalArgumentException("Unsupported serialization type: " + type);
        }
    }
}
