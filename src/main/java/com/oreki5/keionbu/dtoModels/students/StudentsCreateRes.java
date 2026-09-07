package com.oreki5.keionbu.dtoModels.students;

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
    private String studentName;

    public StudentsCreateRes(Students student) {
        id = student.getId();
        studentName = student.getFirstName() + " " + student.getLastName();
    }
}
