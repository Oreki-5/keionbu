package com.oreki5.keionbu.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Students;

public interface StudentsRepo extends MongoRepository<Students, String> {

    boolean existsByUsername(String username);

}
