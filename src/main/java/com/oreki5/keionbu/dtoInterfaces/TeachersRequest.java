package com.oreki5.keionbu.dtoInterfaces;

import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;

import jakarta.activation.UnsupportedDataTypeException;

public interface TeachersRequest {
    Users mapToTeachers(Teachers teacher) throws Exception;
}
