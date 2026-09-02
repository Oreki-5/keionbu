package com.oreki5.keionbu.dbEntities;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
public class BaseEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private Instant createdAt;
    private Instant updatedAt = Instant.now();
}
