package com.oreki5.keionbu.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;

public interface AssignmentsRepo extends MongoRepository<Assignments,String>{

    boolean existsByStudentAndTeacherAndLesson(Students student, Teachers teacher, Lessons lesson);


    List<Assignments> findAllByStudent(Students student);


    List<Assignments> findAllByStudentAndTeacher(Students student, Teachers teacher);


    int countAllByStudentAndTeacherAndLesson(Students student, Teachers teacher, Lessons lesson);
    
}
