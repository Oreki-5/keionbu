package com.oreki5.keionbu.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oreki5.keionbu.dtoInterfaces.StudentsRequest;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersRequest;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserAccountController {

    // Don't forget to change the requetObject to the intented one for each endpoint. also @Valid

    /*
        Teacher account related
    */

    @PostMapping("/teachers")
    public ResponseEntity<TeachersResponse> createTeacher(@RequestBody TeachersRequest teacher) {

        return null;
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeachersResponse> updateTeacher(@PathVariable String id, @RequestBody TeachersRequest request) {
        return null;
    }

    @DeleteMapping("/teachers/{id}")
    public void softDeleteTeacher(@PathVariable String id){

    }

    /*
        Student account related
    */

    @PostMapping("/students")
    public ResponseEntity<StudentsResponse> createStudent(@RequestBody StudentsRequest request) {
        return null;
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentsResponse> updateStudent(@PathVariable String id, @RequestBody StudentsRequest request) {
        return null;
    }

    @DeleteMapping("/students/{id}")
    public void softDeleteStudent(@PathVariable String id){

    }



}
