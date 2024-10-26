package com.example.ruleengine.serializer;

import com.example.ruleengine.model.ASTNode;

public interface Serializer {
    String serialize(ASTNode astNode) throws Exception;
    ASTNode deserialize(String data) throws Exception;
}
