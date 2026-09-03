package com.oreki5.keionbu.dtoModels.teachers;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;

import jakarta.activation.UnsupportedDataTypeException;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TeachersPassReq implements UserAccountRequest {

    @NotEmpty
    private String password;

    @Override
    public Teachers mapToTeachers(Teachers teacher) {
        teacher.setPassword(password);
        teacher.setUpdatedAt(Instant.now());
        return teacher;
    }

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("Invalid Data given");
    }
}
