package com.oreki5.keionbu.dtoModels.students;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;

import jakarta.activation.UnsupportedDataTypeException;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentsUpdateReq implements UserAccountRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @Override
    public Teachers mapToTeachers(Teachers teacher) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("Invalid data given");
    }

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        student.setUsername(username);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUpdatedAt(Instant.now());
        return student;
    }
}
