package com.oreki5.keionbu.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;

public interface AssignmentsRepo extends MongoRepository<Assignments,String>{

    boolean existsByStudentAndTeacherAndLesson(Students student, Teachers teacher, Lessons lesson);

    List<AssignmentsResponse> findAllByStudent(Students student);

    List<AssignmentsResponse> findAllByStudentAndTeacher(Students student, Teachers teacher);
}
