package com.oreki5.keionbu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateReq;
import com.oreki5.keionbu.dtoModels.students.StudentsPassReq;
import com.oreki5.keionbu.dtoModels.students.StudentsUpdateReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersPassReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersUpdateReq;
import com.oreki5.keionbu.services.UserAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    // Don't forget to change the requetObject to the intented one for each
    // endpoint. also @Valid

    /*
     * Teacher account related
     */

    @PostMapping("/teachers")
    public ResponseEntity<?> createTeacher(@RequestBody @Valid TeachersCreateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.createUser(request, "TEACHER"), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id,
            @RequestBody @Valid TeachersUpdateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.updateUser(id, request, "TEACHER"), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/teachers/pass/{id}")
    public ResponseEntity<?> updateTeacherPass(@PathVariable String id,
            @RequestBody @Valid TeachersPassReq request) {
        try {
            userAccountService.updateUser(id, request, "TEACHER");
            return new ResponseEntity<>("Password Updated", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<?> softDeleteTeacher(@PathVariable String id) {
        try {
            userAccountService.softDeleteUser(id, "TEACHER");
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }
    }

    /*
     * Student account related
     */

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentsCreateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.createUser(request, "STUDENT"), HttpStatus.CREATED);
        }
        catch(MethodArgumentNotValidException e ){
            
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id,
            @RequestBody @Valid StudentsUpdateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.updateUser(id, request, "STUDENT"), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/students/pass/{id}")
    public ResponseEntity<?> updateStudentPass(@PathVariable String id,
            @RequestBody @Valid StudentsPassReq request) {
        try {
            userAccountService.updateUser(id, request, "STUDENT");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> softDeleteStudent(@PathVariable String id) {
        try {
            userAccountService.softDeleteUser(id, "STUDENT");
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }
    }

}
