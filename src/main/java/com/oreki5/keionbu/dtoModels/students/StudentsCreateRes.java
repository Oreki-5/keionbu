package com.oreki5.keionbu.dtoModels.students;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentsCreateRes implements UserAccountResponse, StudentsResponse {
    @NotEmpty
    private String id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private String role;

    private String email;
    private String username;
    private Instant createdAt;

    private Instant updatedAt;

    private String token;

    public StudentsCreateRes(Students student) {
        id = student.getId();
        username = student.getUsername();
        firstName = student.getFirstName();
        lastName = student.getLastName();
        role = student.getRole();
        email = student.getEmail();
        createdAt = student.getCreatedAt();
        updatedAt = student.getUpdatedAt();
    }
}
