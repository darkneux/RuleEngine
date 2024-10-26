package com.example.ruleengine.parser;

import com.example.ruleengine.model.ASTNode;

public interface RuleParser {
    ASTNode createRuleAST(String rule);
    ASTNode combineRulesAST(ASTNode rule1AST, ASTNode rule2AST, String operator);
    ASTNode parseFromString(String serializedRule);
}
