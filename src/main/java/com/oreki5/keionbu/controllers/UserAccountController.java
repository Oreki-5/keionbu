package com.oreki5.keionbu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.services.UserAccountService;

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
    public ResponseEntity<UserAccountResponse> createTeacher(@RequestBody UserAccountRequest request) {

        return new ResponseEntity<>(userAccountService.createUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<UserAccountResponse> updateTeacher(@PathVariable String id,
            @RequestBody UserAccountRequest request) {
        return new ResponseEntity<>(userAccountService.updateUser(id, request), HttpStatus.CREATED);

    }

    @DeleteMapping("/teachers/{id}")
    public void softDeleteTeacher(@PathVariable String id) {
        userAccountService.softDeleteUser(id);
    }

    /*
     * Student account related
     */

    @PostMapping("/students")
    public ResponseEntity<UserAccountResponse> createStudent(@RequestBody UserAccountRequest request) {
        return new ResponseEntity<>(userAccountService.createUser(request), HttpStatus.CREATED);

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<UserAccountResponse> updateStudent(@PathVariable String id,
            @RequestBody UserAccountRequest request) {
        return new ResponseEntity<>(userAccountService.updateUser(id, request), HttpStatus.CREATED);

    }

    @DeleteMapping("/students/{id}")
    public void softDeleteStudent(@PathVariable String id) {
        userAccountService.softDeleteUser(id);
    }

}
