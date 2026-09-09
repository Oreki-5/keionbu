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

    @NotEmpty
    private String retypedPassword;

    @Override
    public Teachers mapToTeachers(Teachers teacher) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("Invalid data given");
    }

    @Override
    public Students mapToStudents(Students student) throws Exception {
        if(password != retypedPassword){
            throw new Exception("Passwords don't match");
        }
        student.setPassword(password);
        student.setUpdatedAt(Instant.now());
        return student;
    }
}
