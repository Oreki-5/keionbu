package com.oreki5.keionbu.dtoInterfaces;

import com.oreki5.keionbu.dbEntities.Students;

import jakarta.activation.UnsupportedDataTypeException;

public interface StudentsRequest {
    Students mapToStudents(Students student) throws UnsupportedDataTypeException;
    
}
