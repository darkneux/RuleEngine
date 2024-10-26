package com.example.ruleengine.entity;

import com.example.ruleengine.serializer.SerializationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rules")
@Data
public class RuleEntity {

    @Id
    private String name;

    @Lob
    @Column(nullable = false)
    private String astData;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SerializationType serializationType;
}
