package com.oreki5.keionbu.dbEntities;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="fileMetaData")
public record FileMetaData(
        String storedName,
        String originalName,
        String ownerId,
        String mimeType,
        long size,
        Instant createdAt,
        @Id ObjectId id) {

}
