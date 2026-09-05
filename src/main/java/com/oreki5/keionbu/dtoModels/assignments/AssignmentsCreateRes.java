package com.oreki5.keionbu.dtoModels.assignments;

import com.oreki5.keionbu.dbEntities.Assignments;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;

import lombok.Data;

@Data
public class AssignmentsCreateRes implements AssignmentsResponse {
    private String id;
    private String studentName;
    private String teacherName;
    private String lessonName;
    private String comment;

    public AssignmentsCreateRes(Assignments assignment) {
        id = assignment.getId();
        studentName = assignment.getStudent().getFirstName() + " " + assignment.getStudent().getLastName();
        studentName = assignment.getTeacher().getFirstName() + " " + assignment.getTeacher().getLastName();
        lessonName = assignment.getLesson().getLessonName();
        comment = assignment.getComment();
    }
}
