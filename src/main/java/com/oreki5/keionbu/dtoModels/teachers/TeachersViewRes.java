package com.oreki5.keionbu.dtoModels.teachers;

import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;

import lombok.Data;

@Data
public class TeachersViewRes implements TeachersResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String subject;
    

    public TeachersViewRes(Teachers teacher) {
        id = teacher.getId();
        username = teacher.getUsername();
        firstName = teacher.getFirstName();
        lastName = teacher.getLastName();
        subject = teacher.getSubject();
    }


}
