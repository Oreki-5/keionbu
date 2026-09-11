package com.oreki5.keionbu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/teachers")
    public ResponseEntity<List<TeachersResponse>> getAllTeachers() {
        return new ResponseEntity<>(studentsService.getAllTeachers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#id)")
    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getAllTeachers(@PathVariable String id) {
        try {
            return new ResponseEntity<>(studentsService.getJoinedTeachers(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#request.id)")
    @PostMapping("/teacher/join")
    public ResponseEntity<?> joinTeacher(@RequestBody @Valid StudentsJoinReq request) {
        try {
            return new ResponseEntity<>(studentsService.joinTeacher(request), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#request.id)")
    @PostMapping("/teacher/leave")
    public ResponseEntity<?> leaveTeacher(@RequestBody @Valid StudentsJoinReq request) {
        try {
            return new ResponseEntity<>(studentsService.leaveTeacher(request), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    /*
     * Assignments related
     */

    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#studentId)")
    @GetMapping("/assignments/{studentId}")
    public ResponseEntity<?> getAssignmentsOfStudent(@PathVariable String studentId,
            @RequestParam(required = false) String teacherId) {
        return new ResponseEntity<>(studentsService.getAssignmentsOfStudent(studentId, teacherId), HttpStatus.CREATED);
    }

    // Essentially updating assignment
    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isAssignmentSubmitter(#assignmentId)")
    @PutMapping("/assignment/submit/{assignmentId}")
    public ResponseEntity<?> submitAssignment(@PathVariable String assignmentId,
            @RequestParam MultipartFile submissionFile) {
        try {
            return new ResponseEntity<>(studentsService.submitAssignment(assignmentId, submissionFile),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
