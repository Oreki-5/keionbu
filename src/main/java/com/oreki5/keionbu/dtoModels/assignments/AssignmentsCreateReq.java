package com.oreki5.keionbu.dtoModels.assignments;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;

import jakarta.activation.UnsupportedDataTypeException;
import lombok.Data;

@Data
public class AssignmentsCreateReq implements AssignmentsRequest {
    private Students student;
    private Teachers teacher;
    private Lessons lesson;
    private String comment;

    @Override
    public Assignments mapToAssignment(Assignments assignment) throws UnsupportedDataTypeException {
        assignment.setStudent(student);
        assignment.setTeacher(teacher);
        assignment.setLesson(lesson);
        assignment.setApprovalStatus("pending");
        assignment.setComment(comment);
        assignment.setUpdatedAt(Instant.now());
        return assignment;
    }
}
