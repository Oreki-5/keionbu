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
public class AssignmentsSubmitReq implements AssignmentsRequest {

    private FileMetaData submission;
    private String approvalStatus;


    @Override
    public Assignments mapToAssignment(Assignments assignment) throws UnsupportedDataTypeException {
        assignment.setSubmission(submission);
        assignment.setApprovalStatus(approvalStatus);
        return assignment;
    }
}
