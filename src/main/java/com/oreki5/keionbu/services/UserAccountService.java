package com.oreki5.keionbu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateRes;
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
            case "TEACHER":
                Teachers teacher = request.mapToTeachers(new Teachers());
                if (teachersRepo.existsByUsername(teacher.getUsername())) {
                    throw new Exception("Duplicate Username");
                }
                return new TeachersCreateRes(teachersRepo.save(teacher));
            case "STUDENT":
                Students student = request.mapToStudents(new Students());
                if (studentsRepo.existsByUsername(student.getUsername())) {
                    throw new Exception("Duplicate Username");
                }
                return new StudentsCreateRes(studentsRepo.save(student));
        }
        throw new Exception("unknown exception");
    }

    public UserAccountResponse updateUser(String id, UserAccountRequest request) {
        return null;
    }

    public UserAccountResponse updatePassword(String id, UserAccountRequest request) {
        return null;
    }

    public void softDeleteUser(String id) {

    }

}
