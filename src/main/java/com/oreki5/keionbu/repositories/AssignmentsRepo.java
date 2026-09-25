package com.oreki5.keionbu.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Users;

public interface AssignmentsRepo extends MongoRepository<Assignments,String>{

    boolean existsByStudentAndTeacherAndLesson(Users student, Users teacher, Lessons lesson);


    List<Assignments> findAllByStudent(Users student);


    List<Assignments> findAllByTeacher(Users teacher);


    List<Assignments> findAllByStudentAndTeacher(Users student, Users teacher);


    int countAllByStudentAndTeacherAndLesson(Users student, Users teacher, Lessons lesson);
    
}
