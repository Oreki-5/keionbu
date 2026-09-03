package com.oreki5.keionbu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;
import com.oreki5.keionbu.dtoModels.students.StudentsJoinReq;
import com.oreki5.keionbu.repositories.StudentsRepo;
import com.oreki5.keionbu.repositories.TeachersRepo;

@Service
public class StudentsService {

    @Autowired
    private TeachersRepo teachersRepo;
    @Autowired
    private StudentsRepo studentsRepo;
    /*
     * Teacher joining related
     */

    public List<TeachersResponse> getAllTeachers() {
        return null;
    }

    @Transactional
    public StudentsResponse joinTeacher(StudentsJoinReq request) {

        Students student = studentsRepo.findById(request.getId()).orElseThrow();
        Teachers teacher = teachersRepo.findById(request.getTeacherId()).orElseThrow();

        List<Teachers> exisitingList = student.getTeachersList();
        exisitingList.add(teacher);
        student.setTeachersList(exisitingList);

        return new StudentsCreateRes(student);
    }

    /*
     * Assignments related
     */

    public List<AssignmentsResponse> getAssignmentsOfStudent(String studentId) {
        return null;
    }

    public AssignmentsResponse submitAssignment(String assignmentId, MultipartFile submissionFile) {
        return null;
    }
}
