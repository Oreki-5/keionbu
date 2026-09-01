package com.oreki5.keionbu.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsRequest;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersRequest;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {

    /*
        Registration related
    */
    @PostMapping("/students")
    public StudentsResponse createStudent(@RequestBody StudentsRequest request){
        return null;
    }

    @PutMapping("/students/{id}")
    public StudentsResponse updateStudent(@PathVariable String id, @RequestBody StudentsRequest request){
        return null;
    }


    /*
        Teacher joining related
    */

    @GetMapping("/teachers")
    public ResponseEntity<List<TeachersResponse>> getAllTeachers(){
        return null;
    }

    @PostMapping("/teacher/join/{teacherId}")
    public ResponseEntity<String> joinTeacher(@PathVariable String teacherId){
        return null;
    }


    /*
        Assignments related
    */
    
    @GetMapping("/assignments/{studentId}")
    public ResponseEntity<List<AssignmentsResponse>> getAssignmentsOfStudent(@PathVariable String studentId){
        return null;
    }

    // Essentially updating assignment
    @PutMapping("/assignment/submit/{assignmentId}")
    public ResponseEntity<AssignmentsResponse> submitAssignment(@PathVariable String assignmentId, @RequestBody AssignmentsRequest request){
        return null;
    }


}
