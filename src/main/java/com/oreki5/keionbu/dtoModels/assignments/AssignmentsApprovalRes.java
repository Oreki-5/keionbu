package com.oreki5.keionbu.dtoModels.assignments;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoModels.users.UserShortViewRes;

import lombok.Data;

@Data
public class AssignmentsApprovalRes implements AssignmentsResponse {
    private String id;
    private UserShortViewRes student;
    private UserShortViewRes teacher;
    private Lessons lessonName;
    private String approvalStatus;
    private int score;
    private String comment;
    private Instant createdAt;
    private Instant updatedAt;

    public AssignmentsApprovalRes(Assignments assignment) {
        id = assignment.getId();
        student = new UserShortViewRes(assignment.getStudent());
        teacher = new UserShortViewRes(assignment.getTeacher());
        lessonName = assignment.getLesson();
        comment = assignment.getComment();
        score = assignment.getScore();
        approvalStatus = assignment.getApprovalStatus();
        createdAt = assignment.getCreatedAt();
        updatedAt = assignment.getUpdatedAt();
    }
}
