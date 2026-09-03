package com.oreki5.keionbu.dtoModels.students;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dtoInterfaces.StudentsRequest;

import jakarta.activation.UnsupportedDataTypeException;
import lombok.Data;

@Data
public class StudentsJoinReq implements  StudentsRequest{
    private String id;
    private String teacherId;

    @Override
    public Students mapToStudents(Students student) throws UnsupportedDataTypeException {
        throw new UnsupportedOperationException("this operation is supported for this data");
    }
}
