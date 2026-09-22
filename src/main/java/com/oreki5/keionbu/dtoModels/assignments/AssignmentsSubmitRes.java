package com.oreki5.keionbu.dtoModels.assignments;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;

import lombok.Data;

@Data
public class AssignmentsSubmitRes implements AssignmentsResponse {
    private String id;
    private String studentName;
    private String teacherName;
    private String lessonName;
    private String comment;
    private String approvalStatus;
    private FileMetaData submission;
    private Instant createdAt;
    private Instant updatedAt;

    public AssignmentsSubmitRes(Assignments assignment) {
        id = assignment.getId();
        studentName = assignment.getStudent().getFirstName() + " " + assignment.getStudent().getLastName();
        teacherName = assignment.getTeacher().getFirstName() + " " + assignment.getTeacher().getLastName();
        lessonName = assignment.getLesson().getLessonName();
        approvalStatus = assignment.getApprovalStatus();
        submission = assignment.getSubmission();
        comment = assignment.getComment();
        createdAt = assignment.getCreatedAt();
        updatedAt = assignment.getUpdatedAt();

    }
}
