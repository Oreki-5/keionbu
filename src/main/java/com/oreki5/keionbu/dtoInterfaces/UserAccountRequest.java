package com.oreki5.keionbu.dtoInterfaces;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;

import jakarta.activation.UnsupportedDataTypeException;

public interface UserAccountRequest {

    Teachers mapToTeachers(Teachers teacher) throws UnsupportedDataTypeException;
    Students mapToStudents(Students student) throws UnsupportedDataTypeException;

}
