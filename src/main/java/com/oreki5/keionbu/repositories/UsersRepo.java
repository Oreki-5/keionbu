package com.oreki5.keionbu.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;

public interface UsersRepo extends MongoRepository<Users, String> {
    boolean existsByUsername(String username);

    @Query("{\"role\" : \"ROLE_TEACHER\"}")
    List<Users> findAllTeachers();

    @Query("{\"role\" : \"ROLE_STUDENT\"}")
    List<Students> findAllStudents();

    Users findByUsername(String username);
}
