package com.oreki5.keionbu.dtoModels.students;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;

import lombok.Data;

@Data
public class StudentsViewRes implements StudentsResponse, TeachersResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;

    public StudentsViewRes(Students student) {
        id = student.getId();
        username = student.getUsername();
        firstName = student.getFirstName();
        lastName = student.getLastName();
    }
}
