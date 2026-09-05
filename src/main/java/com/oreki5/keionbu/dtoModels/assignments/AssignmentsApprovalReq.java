package com.oreki5.keionbu.dtoModels.assignments;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;

import jakarta.activation.UnsupportedDataTypeException;
import lombok.Data;

@Data
public class AssignmentsApprovalReq implements AssignmentsRequest {
    
    private Students student;
    private Teachers teacher;
    private Lessons lesson;
    private FileMetaData submission;
    private String approvalStatus;
    private int score;
    private String comment;

    @Override
    public Assignments mapToAssignment(Assignments assignment) throws UnsupportedDataTypeException {

        return assignment;
    }
}
