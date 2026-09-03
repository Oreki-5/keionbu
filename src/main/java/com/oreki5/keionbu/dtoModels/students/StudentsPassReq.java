package com.oreki5.keionbu.dtoModels.students;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;

import jakarta.activation.UnsupportedDataTypeException;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentsPassReq implements UserAccountRequest {

    @NotEmpty
    private String password;

    @Override
    public Teachers mapToTeachers(Teachers teacher) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("Invalid data given");
    }

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        student.setPassword(password);
        student.setUpdatedAt(Instant.now());
        return student;
    }
}
