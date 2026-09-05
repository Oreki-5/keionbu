package com.oreki5.keionbu.dtoModels.students;

import java.util.ArrayList;
import java.util.List;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateRes;

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

    private List<TeachersCreateRes> teachersList;

    public StudentsCreateRes(Students student) {
        id = student.getId();
        firstName = student.getFirstName();
        lastName = student.getLastName();

        teachersList = new ArrayList<>();
        List<Teachers> list = student.getTeachersList();
        list.forEach(teacher -> {
            teachersList.add(new TeachersCreateRes(teacher));
        });
    }
}
