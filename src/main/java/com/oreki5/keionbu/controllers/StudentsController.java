package com.oreki5.keionbu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;
import com.oreki5.keionbu.dtoModels.students.StudentsJoinReq;
import com.oreki5.keionbu.services.FileManagementService;
import com.oreki5.keionbu.services.StudentsService;

import jakarta.validation.Valid;

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

    // @GetMapping("/teachers")
    // public ResponseEntity<List<TeachersResponse>> getAllTeachers() {
    // return new ResponseEntity<>(studentsService.getAllTeachers(), HttpStatus.OK);
    // }
    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getAllTeachers(@PathVariable String id) {
        return new ResponseEntity<>(studentsService.getJoinedTeachers(id), HttpStatus.OK);
    }

    @PostMapping("/teacher/join")
    public ResponseEntity<?> joinTeacher(@RequestBody @Valid StudentsJoinReq request) {
        try {
            return new ResponseEntity<>(studentsService.joinTeacher(request), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    /*
     * Assignments related
     */

    @GetMapping("/assignments/{studentId}")
    public ResponseEntity<List<AssignmentsResponse>> getAssignmentsOfStudent(@PathVariable String studentId,
            @RequestParam String teacherId) {
        return new ResponseEntity<>(studentsService.getAssignmentsOfStudent(studentId, teacherId), HttpStatus.CREATED);
    }

    // Essentially updating assignment
    @PutMapping("/assignment/submit/{assignmentId}")
    public ResponseEntity<AssignmentsResponse> submitAssignment(@PathVariable String assignmentId,
            @RequestParam MultipartFile submissionFile) {
        return new ResponseEntity<>(studentsService.submitAssignment(assignmentId, submissionFile), HttpStatus.CREATED);
    }

}
