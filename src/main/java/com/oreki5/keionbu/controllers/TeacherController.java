package com.oreki5.keionbu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.services.FileManagementService;
import com.oreki5.keionbu.services.TeachersService;

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

    @PostMapping("/lessons")
    public ResponseEntity<LessonsResponse> createLesson(@RequestBody LessonsRequest request,
            @RequestParam MultipartFile lessonFile) {

        return new ResponseEntity<>(teachersService.createLesson(request), HttpStatus.OK);

    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<List<LessonsResponse>> getLessonData(@PathVariable String id) {
        return new ResponseEntity<>(teachersService.getLessonData(id), HttpStatus.OK);

    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<LessonsResponse> updateLesson(@RequestBody LessonsRequest request,
            @RequestParam(required = false) MultipartFile lessonFile) {

        return new ResponseEntity<>(teachersService.updateLessonData(request), HttpStatus.OK);

    }

    @DeleteMapping("/lessons/{id}")
    public void softDeleteLesson(@PathVariable String id) {
        teachersService.softDeleteLesson(id);

    }

    /*
     * Assignment related endpoints
     */

    @PostMapping("/assignments")
    public ResponseEntity<AssignmentsResponse> createAssignment(@RequestBody AssignmentsRequest request) {
        return new ResponseEntity<>(teachersService.createAssignment(request), HttpStatus.OK);

    }

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentsResponse>> getAssignmentsWithFilters(
            @RequestParam(required = false) String studentId) {
        return new ResponseEntity<>(teachersService.getAssignmentsWithFilters(studentId), HttpStatus.OK);

    }

    @PutMapping("/assignments/edit/{id}")
    public ResponseEntity<AssignmentsResponse> editAssignment(@RequestBody AssignmentsRequest request) {
        return new ResponseEntity<>(teachersService.editAssignment(request), HttpStatus.OK);

    }

    @PutMapping("/assignments/approve/{id}")
    public ResponseEntity<AssignmentsResponse> approveAssignment(@RequestBody AssignmentsRequest request) {
        return new ResponseEntity<>(teachersService.approveAssignment(request), HttpStatus.OK);
    }

    @DeleteMapping("/assignments/{id}")
    public void deleteAssignment(@PathVariable String id) {
        teachersService.deleteAssignment(id);
    }

}
