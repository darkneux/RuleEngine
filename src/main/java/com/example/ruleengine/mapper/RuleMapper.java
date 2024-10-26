package com.example.ruleengine.mapper;

import com.example.ruleengine.dto.RuleDTO;
import com.example.ruleengine.entity.RuleEntity;

public class RuleMapper {


    public static RuleDTO toDTO(RuleEntity ruleEntity) {
        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setName(ruleEntity.getName());
        ruleDTO.setRule(ruleEntity.getAstData());
        ruleDTO.setSerializationType(ruleEntity.getSerializationType());
        return ruleDTO;
    }


    public static RuleEntity toEntity(RuleDTO ruleDTO) {
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setName(ruleDTO.getName());
        ruleEntity.setAstData(ruleDTO.getRule());
        ruleEntity.setSerializationType(ruleDTO.getSerializationType());
        return ruleEntity;
    }
}
