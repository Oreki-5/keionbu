package com.oreki5.keionbu.services;

import com.oreki5.keionbu.controllers.GlobalExceptionHandler;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.dtoModels.auth.OtpVerificationReq;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsPassReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateRes;
import com.oreki5.keionbu.dtoModels.teachers.TeachersPassReq;
import com.oreki5.keionbu.repositories.StudentsRepo;
import com.oreki5.keionbu.repositories.TeachersRepo;

@Service
public class UserAccountService {

    private final GlobalExceptionHandler globalExceptionHandler;
    @Autowired
    private TeachersRepo teachersRepo;
    @Autowired
    private StudentsRepo studentsRepo;

    @Autowired
    private EmailService mailService;

    UserAccountService(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Transactional
    public UserAccountResponse createUser(UserAccountRequest request, String role) throws Exception {

        switch (role) {
            case "TEACHER" -> {
                Teachers teacher = request.mapToTeachers(new Teachers());
                teacher.setPassword(passEncoder(teacher.getPassword()));
                if (teachersRepo.existsByUsername(teacher.getUsername())) {
                    throw new Exception("Duplicate Username");
                }
                teacher.setOtp(sendOtp(teacher));
                return new TeachersCreateRes(teachersRepo.save(teacher));

            }
            case "STUDENT" -> {
                Students student = request.mapToStudents(new Students());
                student.setPassword(passEncoder(student.getPassword()));
                if (studentsRepo.existsByUsername(student.getUsername())) {
                    throw new Exception("Duplicate Username");
                }
                student.setOtp(sendOtp(student));
                return new StudentsCreateRes(studentsRepo.save(student));
            }
        }
        throw new UnsupportedOperationException("Invalid data provided");
    }

    public UserAccountResponse verifyOtp(OtpVerificationReq request, String role) {
        switch (role) {
            case "TEACHER" -> {
                Teachers teacher = teachersRepo.findById(request.getId()).orElseThrow();
                if (teacher.getOtp().equals(request.getOtp())) {
                    teacher.setOtp("");
                    teacher.setVerified(true);
                    return new TeachersCreateRes(teachersRepo.save(teacher));

                } else {
                    throw new SecurityException("OTP is not correct");
                }

            }
            case "STUDENT" -> {
                Students student = studentsRepo.findById(request.getId()).orElseThrow();
                if (student.getOtp().equals(request.getOtp())) {
                    student.setOtp("");
                    student.setVerified(true);
                    return new StudentsCreateRes(studentsRepo.save(student));

                } else {
                    throw new SecurityException("OTP is not correct");
                }
            }
        }
        throw new UnsupportedOperationException("Invalid data provided");

    }

    public UserAccountResponse updateUser(String id, UserAccountRequest request, String role) throws Exception {
        switch (role) {
            case "TEACHER" -> {

                Teachers teacher = request.mapToTeachers(teachersRepo.findById(id).orElseThrow());
                if (request instanceof TeachersPassReq) {
                    teacher.setPassword(passEncoder(teacher.getPassword()));
                }
                return new TeachersCreateRes(teachersRepo.save(teacher));
            }
            case "STUDENT" -> {

                Students student = request.mapToStudents(studentsRepo.findById(id).orElseThrow());
                if (request instanceof StudentsPassReq) {
                    student.setPassword(passEncoder(student.getPassword()));
                }
                return new StudentsCreateRes(studentsRepo.save(student));
            }
        }
        throw new UnsupportedOperationException("Invalid data provided");

    }

    public void softDeleteUser(String id, String role) throws IllegalArgumentException {
        switch (role) {
            case "TEACHER" -> {
                teachersRepo.deleteById(id);
            }
            case "STUDENT" -> {
                studentsRepo.deleteById(id);
            }
        }
    }

    
    public String passEncoder(String input) {

        return "encyrpted:" + input;

    }
    
    public String sendOtp(Users user) {
        Random r = new Random();
        String otp = String.format("%06d", r.nextInt(100000));

        String subject = "Account Verification for Keionbu user: " + user.getUsername();
        String body = "Your OTP for verification is: " + otp;
        try {
            mailService.sendEmail(user.getEmail(), subject, body);

        } catch (Exception e) {

        }
        return otp;
    }

}
