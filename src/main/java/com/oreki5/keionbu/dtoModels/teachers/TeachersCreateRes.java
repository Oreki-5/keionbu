package com.oreki5.keionbu.dtoModels.teachers;

import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TeachersCreateRes implements UserAccountResponse {
    @NotEmpty
    private String id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String subject;

    public TeachersCreateRes(Teachers teacher) {
        id = teacher.getId();
        username = teacher.getUsername();
        firstName = teacher.getFirstName();
        lastName = teacher.getLastName();
        subject = teacher.getSubject();
    }

}
