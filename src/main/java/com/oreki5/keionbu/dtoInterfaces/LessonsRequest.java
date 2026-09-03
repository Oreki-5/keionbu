package com.oreki5.keionbu.dtoInterfaces;

import com.oreki5.keionbu.dbEntities.Lessons;

import jakarta.activation.UnsupportedDataTypeException;

public interface LessonsRequest {
    Lessons mapToLessons(Lessons lesson) throws UnsupportedDataTypeException;
}
