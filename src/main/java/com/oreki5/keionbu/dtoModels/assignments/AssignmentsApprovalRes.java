package com.oreki5.keionbu.dtoModels.assignments;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;

import lombok.Data;

@Data
public class AssignmentsApprovalRes implements AssignmentsResponse {
    private String id;
    private String studentName;
    private String lessonName;
    private String approvalStatus;
    private int score;
    private String comment;

    public AssignmentsApprovalRes(Assignments assignment) {
        id = assignment.getId();
        studentName = assignment.getStudent().getFirstName() + " " + assignment.getStudent().getLastName();
        lessonName = assignment.getLesson().getLessonName();
        comment = assignment.getComment();
        score = assignment.getScore();
        approvalStatus = assignment.getApprovalStatus();
    }
}
