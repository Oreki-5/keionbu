package com.oreki5.keionbu.dtoModels.teachers;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;

import jakarta.activation.UnsupportedDataTypeException;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TeachersUpdateReq implements UserAccountRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String subject;

    @Override
    public Teachers mapToTeachers(Teachers teacher) {
        teacher.setUsername(username);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setUpdatedAt(Instant.now());
        teacher.setSubject(subject);
        return teacher;
    }

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("Invalid Data given");
    }
}
