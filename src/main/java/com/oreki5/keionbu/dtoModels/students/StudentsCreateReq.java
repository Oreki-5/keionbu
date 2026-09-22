package com.oreki5.keionbu.dtoModels.students;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.utils.UserRolesEnum;

import jakarta.activation.UnsupportedDataTypeException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentsCreateReq implements UserAccountRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String email;



    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        student.setUsername(username);
        student.setPassword(password);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUpdatedAt(Instant.now());
        student.setEmail(email);
        student.setRole(UserRolesEnum.STUDENT.getRole());
        student.setCreatedAt(Instant.now());
        student.setUpdatedAt(Instant.now());
        return student;
    }

    @Override
    public Teachers mapToTeachers(Teachers teacher) throws UnsupportedDataTypeException {
        throw new UnsupportedDataTypeException("Invalid data");
    }

}
