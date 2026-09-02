package com.oreki5.keionbu.dtoModels.students;

import java.util.List;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import lombok.Data;

@Data
public class StudentsCreateRes implements UserAccountResponse {
    private String firstName;
    private String lastName;

    public StudentsCreateRes(Students student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();
    }
}
