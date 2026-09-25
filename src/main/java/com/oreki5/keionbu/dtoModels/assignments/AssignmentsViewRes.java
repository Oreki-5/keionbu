package com.oreki5.keionbu.dtoModels.assignments;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoModels.lessons.LessonsViewRes;
import com.oreki5.keionbu.dtoModels.users.UserShortViewRes;

import lombok.Data;

@Data
public class AssignmentsViewRes implements AssignmentsResponse {
    private String id;
    private UserShortViewRes student;
    private UserShortViewRes teacher;
    private LessonsViewRes lesson;
    private String approvalStatus;


    public AssignmentsViewRes(Assignments assignment) {
        id = assignment.getId();
        student = new UserShortViewRes(assignment.getStudent());
        teacher = new UserShortViewRes(assignment.getTeacher());
        lesson = new LessonsViewRes(assignment.getLesson());
        approvalStatus = assignment.getApprovalStatus();

    }
}
