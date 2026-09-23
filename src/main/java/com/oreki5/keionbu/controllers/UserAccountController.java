package com.oreki5.keionbu.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oreki5.keionbu.config.JwtService;
import com.oreki5.keionbu.dtoModels.auth.LoginReq;
import com.oreki5.keionbu.dtoModels.auth.OtpVerificationReq;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateReq;
import com.oreki5.keionbu.dtoModels.students.StudentsPassReq;
import com.oreki5.keionbu.dtoModels.students.StudentsUpdateReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersPassReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersUpdateReq;
import com.oreki5.keionbu.services.EmailService;
import com.oreki5.keionbu.services.UserAccountService;
import com.oreki5.keionbu.utils.UserRolesEnum;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private EmailService mailService;

    @Autowired
    private JwtService jwtService;

    // Don't forget to change the requetObject to the intented one for each
    // endpoint. also @Valid

    /*
     * Teacher account related
     */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq request) throws NoSuchAlgorithmException {
        try {
            return new ResponseEntity<>(userAccountService.loginUser(request),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> createTeacher(@RequestBody @Valid TeachersCreateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.createUser(request), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teachers/verify")
    public ResponseEntity<?> verifyTeacher(@RequestBody @Valid OtpVerificationReq request) {
        try {
            return new ResponseEntity<>(userAccountService.verifyOtp(request, UserRolesEnum.TEACHER),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/teachers/{id}")
    @PreAuthorize("hasRole('TEACHER') and @preAuthService.isOwner(#id)")
    public ResponseEntity<?> updateTeacher(@PathVariable String id,
            @RequestBody @Valid TeachersUpdateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.updateUser(id, request), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/teachers/pass/{id}")
    @PreAuthorize("hasRole('TEACHER') and @preAuthService.isOwner(#id)")
    public ResponseEntity<?> updateTeacherPass(@PathVariable String id,
            @RequestBody @Valid TeachersPassReq request) {
        try {
            userAccountService.updateUser(id, request);
            return new ResponseEntity<>("Password Updated", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasRole('TEACHER') and @preAuthService.isOwner(#id)")
    public ResponseEntity<?> softDeleteTeacher(@PathVariable String id) {
        try {
            userAccountService.softDeleteUser(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }
    }

    // @GetMapping("/test")
    // @PreAuthorize("@preAuthService.isOwner(#userId)")
    // public String getMethodName(@RequestParam("userId") String userId) {
    // return "you authorized";
    // }

    /*
     * Student account related
     */

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentsCreateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.createUser(request), HttpStatus.CREATED);
        } catch (MethodArgumentNotValidException e) {

            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/students/verify")
    public ResponseEntity<?> verifyStudents(@RequestBody @Valid OtpVerificationReq request) {
        try {
            return new ResponseEntity<>(userAccountService.verifyOtp(request, UserRolesEnum.STUDENT),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/students/{id}")
    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#id)")
    public ResponseEntity<?> updateStudent(@PathVariable String id,
            @RequestBody @Valid StudentsUpdateReq request) {
        try {
            return new ResponseEntity<>(userAccountService.updateUser(id, request), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/students/pass/{id}")
    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#id)")
    public ResponseEntity<?> updateStudentPass(@PathVariable String id,
            @RequestBody @Valid StudentsPassReq request) {
        try {
            userAccountService.updateUser(id, request);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/students/{id}")
    @PreAuthorize("hasRole('STUDENT') and @preAuthService.isOwner(#id)")
    public ResponseEntity<?> softDeleteStudent(@PathVariable String id) {
        try {
            userAccountService.softDeleteUser(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }
    }

}
