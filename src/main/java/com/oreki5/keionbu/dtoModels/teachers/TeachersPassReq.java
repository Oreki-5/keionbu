package com.oreki5.keionbu.dtoModels.teachers;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.TeachersRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;

import jakarta.activation.UnsupportedDataTypeException;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TeachersPassReq implements UserAccountRequest, TeachersRequest {

    @NotEmpty
    private String password;
    @NotEmpty
    private String retypedPassword;

    @Override
    public Teachers mapToTeachers(Teachers teacher) throws Exception {
        if(password != retypedPassword){
            throw new Exception("Passwords don't match");
        }
        teacher.setPassword(password);
        teacher.setUpdatedAt(Instant.now());
        return teacher;
    }

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("Invalid Data given");
    }
}
