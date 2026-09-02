package com.oreki5.keionbu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;
import com.oreki5.keionbu.services.FileManagementService;
import com.oreki5.keionbu.services.StudentsService;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {


    @Autowired
    private StudentsService studentsService;
    @Autowired
    private FileManagementService fileManagementService;

    /*
     * Teacher joining related
     */

    @GetMapping("/teachers")
    public ResponseEntity<List<TeachersResponse>> getAllTeachers() {
        return new ResponseEntity<>(studentsService.getAllTeachers(), HttpStatus.OK);
    }

    @PostMapping("/teacher/join/{teacherId}")
    public ResponseEntity<String> joinTeacher(@PathVariable String teacherId) {
        return new ResponseEntity<>(studentsService.joinTeacher(teacherId),HttpStatus.CREATED);
    }

    /*
     * Assignments related
     */

    @GetMapping("/assignments/{studentId}")
    public ResponseEntity<List<AssignmentsResponse>> getAssignmentsOfStudent(@PathVariable String studentId) {
        return new ResponseEntity<>(studentsService.getAssignmentsOfStudent(studentId),HttpStatus.CREATED);
    }

    // Essentially updating assignment
    @PutMapping("/assignment/submit/{assignmentId}")
    public ResponseEntity<AssignmentsResponse> submitAssignment(@PathVariable String assignmentId,
            @RequestParam MultipartFile submissionFile) {
        return new ResponseEntity<>(studentsService.submitAssignment(assignmentId, submissionFile), HttpStatus.CREATED);
    }

}
