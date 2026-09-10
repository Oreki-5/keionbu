package com.oreki5.keionbu.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;

public interface UsersRepo extends MongoRepository<Users, String> {
    boolean existsByUsername(String username);
}
