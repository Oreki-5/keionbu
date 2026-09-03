package com.oreki5.keionbu.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Teachers;

public interface LessonsRepo extends MongoRepository<Lessons, String> {
    boolean existsByLessonNoAndTeacher(long lessonNo, Teachers teacher);
}
