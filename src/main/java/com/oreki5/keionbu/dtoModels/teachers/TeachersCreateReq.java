package com.oreki5.keionbu.dtoModels.teachers;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;

import jakarta.activation.UnsupportedDataTypeException;
import lombok.Data;

@Data
public class TeachersCreateReq implements UserAccountRequest {
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String subject;

    @Override
    public Teachers mapToTeachers(Teachers teacher){
        

        return teacher;
    }

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        throw new UnsupportedDataTypeException("entered invalid data");
    }

}
