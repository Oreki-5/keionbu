package com.oreki5.keionbu.dtoModels.assignments;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AssignmentsApprovalReq implements AssignmentsRequest {

    @NotEmpty
    private String approvalStatus;
    private int score;
    private String comment;

    @Override
    public Assignments mapToAssignment(Assignments assignment) {
        assignment.setApprovalStatus(approvalStatus);
        assignment.setScore(score);
        assignment.setComment(comment);
        assignment.setUpdatedAt(Instant.now());

        return assignment;
    }
}
