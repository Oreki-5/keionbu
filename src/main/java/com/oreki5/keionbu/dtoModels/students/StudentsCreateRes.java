package com.oreki5.keionbu.dtoModels.students;

import java.util.ArrayList;
import java.util.List;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
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

    private List<Teachers> teachersList;

    public StudentsCreateRes(Students student) {
        id = student.getId();
        firstName = student.getFirstName();
        lastName = student.getLastName();
        teachersList = student.getTeachersList();
    }
}
