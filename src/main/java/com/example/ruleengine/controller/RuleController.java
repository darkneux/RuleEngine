package com.example.ruleengine.controller;

import com.example.ruleengine.dto.RuleDTO;
import com.example.ruleengine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/create")
    public RuleDTO saveRule(@RequestBody RuleDTO ruleDTO) {
        ruleService.createRule(ruleDTO);
        return ruleDTO;
    }

    @GetMapping("/{name}")
    public RuleDTO getRuleByName(@PathVariable String name) {
        return ruleService.getRuleByName(name);
    }

    @PostMapping("/evaluate")
    public String evaluateRule(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("rule-name");
        Map<String, Object> data = (Map<String, Object>) requestBody.get("data");

        System.out.println(":::::::::::::::::   "+name);

        boolean result = ruleService.evaluateRule(name, data);
        return result ? "Data is valid." : "Data validation failed.";
    }
}
