package com.oreki5.keionbu.dtoModels.assignments;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoModels.lessons.LessonsViewRes;
import com.oreki5.keionbu.dtoModels.users.UserShortViewRes;

import lombok.Data;

@Data
public class AssignmentsCreateRes implements AssignmentsResponse {
    private String id;
    private UserShortViewRes student;
    private UserShortViewRes teacher;
    private LessonsViewRes lesson;
    private String approvalStatus;
    private int score;
    private String comment;
    private Instant createdAt;
    private Instant updatedAt;
    private FileMetaData submission;

    public AssignmentsCreateRes(Assignments assignment) {
        id = assignment.getId();
        student = new UserShortViewRes(assignment.getStudent());
        teacher = new UserShortViewRes(assignment.getTeacher());
        lesson = new LessonsViewRes(assignment.getLesson());
        comment = assignment.getComment();
        score = assignment.getScore();
        approvalStatus = assignment.getApprovalStatus();
        createdAt = assignment.getCreatedAt();
        updatedAt = assignment.getUpdatedAt();
        submission = assignment.getSubmission();

    }
}
