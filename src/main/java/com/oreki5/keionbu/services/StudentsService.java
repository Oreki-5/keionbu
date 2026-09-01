package com.oreki5.keionbu.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
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

    public AssignmentsResponse submitAssignment(String assignmentId, AssignmentsRequest request) {
        return null;
    }
}
