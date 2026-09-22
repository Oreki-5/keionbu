package com.oreki5.keionbu.dtoModels.teachers;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TeachersCreateRes implements UserAccountResponse {
    @NotEmpty
    private String id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String subject;

    private String role;

    private String email;
    private String username;

    private Instant createdAt;

    private Instant updatedAt;

    private String token;

    public TeachersCreateRes(Teachers teacher) {
        id = teacher.getId();
        username = teacher.getUsername();
        firstName = teacher.getFirstName();
        lastName = teacher.getLastName();
        subject = teacher.getSubject();
        email = teacher.getEmail();
        createdAt = teacher.getCreatedAt();
        updatedAt = teacher.getUpdatedAt();
        role = teacher.getRole();
    }

}
