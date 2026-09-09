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
    private String username;
    @NotEmpty
    private String studentName;
    @NotEmpty
    private String email;
    @NotEmpty
    private boolean verified;

    public StudentsCreateRes(Students student) {
        id = student.getId();
        username = student.getUsername();
        studentName = student.getFirstName() + " " + student.getLastName();
        email = student.getEmail();
        verified = student.isVerified();
    }
}
