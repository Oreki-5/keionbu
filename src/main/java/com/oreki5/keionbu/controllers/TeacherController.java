package com.oreki5.keionbu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsApprovalReq;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsCreateReq;
import com.oreki5.keionbu.dtoModels.lessons.LessonsCreateReq;
import com.oreki5.keionbu.services.FileManagementService;
import com.oreki5.keionbu.services.TeachersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class TeacherController {

    @Autowired
    private TeachersService teachersService;
    @Autowired
    private FileManagementService fileManagementService;

    /*
     * Teacher related endpoints
     */

    @GetMapping("/teachers/{id}/students")
    public ResponseEntity<List<StudentsResponse>> getStudentsOfTeacher(@PathVariable String id) {
        return new ResponseEntity<>(teachersService.getStudentsOfTeacher(id), HttpStatus.OK);
    }

    /*
     * Music lessons related endpoints
     */

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @PostMapping("/lessons/{teacherId}")
    public ResponseEntity<?> createLesson(@PathVariable String teacherId,
            @RequestPart(value = "lessonfile", required = false) MultipartFile lessonFile,
            @RequestPart(value = "request") @Valid LessonsCreateReq request) {
        try {
            return new ResponseEntity<>(teachersService.createLesson(request, teacherId, lessonFile), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.OK);

        }

    }

    // Future update : add auth so that only the lesson author can access that
    // record
    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonsResponse> getLessonData(@PathVariable String id) {
        return new ResponseEntity<>(teachersService.getLessonData(id), HttpStatus.OK);

    }

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @PutMapping("/lessons/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable String id,
            @RequestPart(value = "lessonfile", required = false) MultipartFile lessonFile,
            @RequestPart(value = "request") @Valid LessonsCreateReq request) {
        try {
            return new ResponseEntity<>(teachersService.updateLessonData(request, id, lessonFile), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.OK);

        }

    }

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @DeleteMapping("/lessons/{id}")
    public void softDeleteLesson(@PathVariable String id) {
        teachersService.softDeleteLesson(id);

    }

    /*
     * Assignment related endpoints
     */

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @PostMapping("/assignments")
    public ResponseEntity<?> createAssignment(@RequestBody @Valid AssignmentsCreateReq request) {
        try {
            return new ResponseEntity<>(teachersService.createAssignment(request), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }

    }

    // filters will come later
    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentsResponse>> getAssignmentsWithFilters(
            @RequestParam(required = false) String studentId) {
        return new ResponseEntity<>(teachersService.getAssignmentsWithFilters(studentId, ""), HttpStatus.OK);

    }

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @PutMapping("/assignments/edit/{id}")
    public ResponseEntity<?> editAssignment(@PathVariable String id,
            @RequestBody AssignmentsCreateReq request) {
        try {
            return new ResponseEntity<>(teachersService.editAssignment(request, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @PutMapping("/assignments/approve/{id}")
    public ResponseEntity<?> approveAssignment(@PathVariable String id,
            @RequestBody AssignmentsApprovalReq request) {
        try {
            return new ResponseEntity<>(teachersService.editAssignment(request, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole(ROLE_TEACHER)")
    @DeleteMapping("/assignments/{id}")
    public void deleteAssignment(@PathVariable String id) {
        try {
            teachersService.deleteAssignment(id);
        } catch (Exception e) {

        }
    }

}
