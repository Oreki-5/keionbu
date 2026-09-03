package com.oreki5.keionbu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsPassReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateRes;
import com.oreki5.keionbu.dtoModels.teachers.TeachersPassReq;
import com.oreki5.keionbu.repositories.StudentsRepo;
import com.oreki5.keionbu.repositories.TeachersRepo;

@Service
public class UserAccountService {

    @Autowired
    private TeachersRepo teachersRepo;
    @Autowired
    private StudentsRepo studentsRepo;

    public UserAccountResponse createUser(UserAccountRequest request, String role) throws Exception {

        switch (role) {
            case "TEACHER" -> {
                Teachers teacher = request.mapToTeachers(new Teachers());
                teacher.setPassword(passEncoder(teacher.getPassword()));
                if (teachersRepo.existsByUsername(teacher.getUsername())) {
                    throw new Exception("Duplicate Username");
                }
                return new TeachersCreateRes(teachersRepo.save(teacher));
            }
            case "STUDENT" -> {
                Students student = request.mapToStudents(new Students());
                student.setPassword(passEncoder(student.getPassword()));
                if (studentsRepo.existsByUsername(student.getUsername())) {
                    throw new Exception("Duplicate Username");
                }
                return new StudentsCreateRes(studentsRepo.save(student));
            }
        }
        throw new UnsupportedOperationException("Invalid data provided");
    }

    public UserAccountResponse updateUser(String id, UserAccountRequest request, String role) throws Exception {
        switch (role) {
            case "TEACHER" -> {

                Teachers teacher = request.mapToTeachers(teachersRepo.findById(id).orElseThrow());
                if (request instanceof TeachersPassReq) {
                    teacher.setPassword(passEncoder(teacher.getPassword()));
                }
                return new TeachersCreateRes(teachersRepo.save(teacher));
            }
            case "STUDENT" -> {

                Students student = request.mapToStudents(studentsRepo.findById(id).orElseThrow());
                if (request instanceof StudentsPassReq) {
                    student.setPassword(passEncoder(student.getPassword()));
                }
                return new StudentsCreateRes(studentsRepo.save(student));
            }
        }
        throw new UnsupportedOperationException("Invalid data provided");

    }

    public void softDeleteUser(String id, String role) throws IllegalArgumentException {
        switch (role) {
            case "TEACHER" -> {
                teachersRepo.deleteById(id);
            }
            case "STUDENT" -> {
                studentsRepo.deleteById(id);
            }
        }
    }

    public String passEncoder(String input) {

        return "encyrpted:" + input;
    }

}
