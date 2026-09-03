package com.oreki5.keionbu.dtoModels.students;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentsCreateRes implements UserAccountResponse {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public StudentsCreateRes(Students student) {
        firstName = student.getFirstName();
        lastName = student.getLastName();
    }
}
