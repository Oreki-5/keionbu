package com.oreki5.keionbu.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Users;

public interface LessonsRepo extends MongoRepository<Lessons, String> {
    boolean existsByLessonNoAndTeacher(long lessonNo, Users teacher);

    List<Lessons> findAllByTeacher(Users teacher);
}
