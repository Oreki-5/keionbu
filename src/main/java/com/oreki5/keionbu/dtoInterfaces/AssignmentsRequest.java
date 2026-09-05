package com.oreki5.keionbu.dtoInterfaces;

import com.oreki5.keionbu.dbEntities.Assignments;

import jakarta.activation.UnsupportedDataTypeException;

public interface AssignmentsRequest {

    Assignments mapToAssignment(Assignments assignment) throws UnsupportedDataTypeException;

}
