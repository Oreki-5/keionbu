package com.oreki5.keionbu.dtoModels.teachers;

import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import lombok.Data;

@Data
public class TeachersCreateRes implements UserAccountResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String subject;

    public TeachersCreateRes(Teachers teacher) {
        username = teacher.getUsername();
        firstName = teacher.getFirstName();
        lastName = teacher.getLastName();
        subject = teacher.getSubject();
    }

}
