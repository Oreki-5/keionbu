package com.oreki5.keionbu.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.FileMetaData;

import org.bson.types.ObjectId;

public interface FileMetaDataRepo extends MongoRepository<FileMetaData, ObjectId> {

}
