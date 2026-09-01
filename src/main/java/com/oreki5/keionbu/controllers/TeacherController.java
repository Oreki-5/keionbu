package com.oreki5.keionbu.controllers;

import java.util.List;

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

import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;




@RestController
@RequestMapping("api/v1")
public class TeacherController {

    /*
        Teacher related endpoints
    */

    

    @GetMapping("/teachers/{id}/students")
    public ResponseEntity<List<StudentsResponse>> getStudentsOfTeacher(@PathVariable String id) {
        return null;
    }


    /*
        Music lessons related endpoints
    */
    
    @PostMapping("/lessons")
    public ResponseEntity<LessonsResponse> createLesson(@RequestBody LessonsRequest request) {

        
        return null;
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<List<LessonsResponse>> getLessonData(@PathVariable String id){
        return null;
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<LessonsResponse> updateLessonData(@RequestBody LessonsRequest request){

        return null;
    }

    @DeleteMapping("/lessons/{id}")
    public void softDeleteLesson(@PathVariable String id){

    }


    /*
        Assignment related endpoints
    */

    @PostMapping("/assignments")
    public ResponseEntity<AssignmentsResponse> createAssignment(@RequestBody AssignmentsRequest request){
        return null;
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentsResponse>> getAssignmentsWithFilters(@RequestParam(required = false) String studentId){
        return null;
    }

    @PutMapping("/assignments/edit/{id}")
    public ResponseEntity<AssignmentsResponse> editAssignment(@RequestBody AssignmentsRequest request){
        return null;
    }

    @PutMapping("/assignments/approve/{id}")
    public ResponseEntity<AssignmentsResponse> approveAssignment(@RequestBody AssignmentsRequest request){
        return null;
    }
    
    @DeleteMapping("/assignments/{id}")
    public void deleteAssignment(@PathVariable String id){

    }


}
