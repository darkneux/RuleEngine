package com.example.ruleengine.config;

import com.example.ruleengine.validation.AttributeCatalog;
import com.example.ruleengine.validation.ValidatableAttribute;
import com.example.ruleengine.validation.NonValidatableAttribute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleEngineConfig {

    @Bean
    public AttributeCatalog attributeCatalog() {
        AttributeCatalog catalog = new AttributeCatalog();
        catalog.addAttribute(new ValidatableAttribute("age", Integer.class, value -> {
            if ((Integer) value < 0) {
                throw new IllegalArgumentException("Value for age must be non-negative.");
            }
        }));
        catalog.addAttribute(new ValidatableAttribute("salary", Integer.class, value -> {
            if ((Integer) value < 0) {
                throw new IllegalArgumentException("Value for salary must be non-negative.");
            }
        }));
        catalog.addAttribute(new ValidatableAttribute("experience",Integer.class,value -> {
            if ((Integer) value < 0) {
                throw new IllegalArgumentException("Value for experience must be non-negative.");
            }
        }));

        catalog.addAttribute(new NonValidatableAttribute("department", String.class));
        catalog.addAttribute(new NonValidatableAttribute("gender", String.class));



        return catalog;
    }
}
