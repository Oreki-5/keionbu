package com.oreki5.keionbu.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;

@Service
public class StudentsService {

    /*
     * Teacher joining related
     */
    
    public List<TeachersResponse> getAllTeachers() {
        return null;
    }

    public String joinTeacher(String teacherId) {
        return null;
    }

    /*
     * Assignments related
     */

    public List<AssignmentsResponse> getAssignmentsOfStudent(String studentId) {
        return null;
    }

    public AssignmentsResponse submitAssignment(String assignmentId, MultipartFile submissionFile) {
        return null;
    }
}
