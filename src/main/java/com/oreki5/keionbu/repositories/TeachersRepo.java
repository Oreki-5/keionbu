package com.oreki5.keionbu.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Teachers;

public interface TeachersRepo extends MongoRepository<Teachers,String>{

    Optional<Teachers> findByUsername(String username);

    boolean existsByUsername(String username);

}
