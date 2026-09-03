package com.oreki5.keionbu.dbEntities;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public record FileMetaData(String originalName,
    String storedName,
    String ownerId,
    String mimeType,
    long size,
    Instant createdAt,
    @Id ObjectId id
) {

}