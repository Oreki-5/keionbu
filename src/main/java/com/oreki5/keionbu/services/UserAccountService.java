package com.oreki5.keionbu.services;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oreki5.keionbu.config.JwtService;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;
import com.oreki5.keionbu.dtoInterfaces.TeachersRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;
import com.oreki5.keionbu.dtoModels.auth.LoginReq;
import com.oreki5.keionbu.dtoModels.auth.OtpVerificationReq;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsPassReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersCreateRes;
import com.oreki5.keionbu.dtoModels.teachers.TeachersPassReq;
import com.oreki5.keionbu.repositories.UsersRepo;
import com.oreki5.keionbu.utils.UserRolesEnum;

@Service
public class UserAccountService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private EmailService mailService;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public UserAccountResponse createUser(UserAccountRequest request) throws Exception {
        Users user;
        if (request instanceof TeachersCreateReq) {
            user = request.mapToTeachers(new Teachers());

        } else {
            user = request.mapToStudents(new Students());
        }
        user.setPassword(passEncoder(user.getPassword()));

        if (usersRepo.existsByUsername(user.getUsername())) {
            throw new Exception("Duplicate Record");
        }

        Random r = new Random();
        String otp = String.format("%06d", r.nextInt(100000));

        user.setOtp(otp);
        user.setCreatedAt(Instant.now());

        CompletableFuture.runAsync(() -> {
            sendOtp(user);
        });

        return request instanceof TeachersCreateReq
                ? new TeachersCreateRes(usersRepo.save((Teachers) user))
                : new StudentsCreateRes(usersRepo.save((Students) user));

        // switch (role) {
        // case "TEACHER" -> {
        // Teachers teacher =
        // teacher.setPassword(passEncoder(teacher.getPassword()));
        // if (usersRepo.existsByUsername(teacher.getUsername())) {
        // throw new Exception("Duplicate Username");
        // }
        // teacher.setOtp(sendOtp(teacher));
        // return new TeachersCreateRes(usersRepo.save(teacher));

        // }
        // case "STUDENT" -> {
        // Students student = request.mapToStudents(new Students());
        // student.setPassword(passEncoder(student.getPassword()));
        // if (usersRepo.existsByUsername(student.getUsername())) {
        // throw new Exception("Duplicate Username");
        // }
        // student.setOtp(sendOtp(student));
        // return new StudentsCreateRes(usersRepo.save((Students) student));
        // }
        // }
        // throw new UnsupportedOperationException("Invalid data provided");
    }

    public UserAccountResponse verifyOtp(OtpVerificationReq request, UserRolesEnum role) {

        Users user;
        if (role == UserRolesEnum.TEACHER) {
            user = (Teachers) usersRepo.findById(request.getId()).orElseThrow();

        } else {
            user = (Students) usersRepo.findById(request.getId()).orElseThrow();
        }
        if (user.getOtp().equals(request.getOtp())) {
            user.setOtp("");
            user.setVerified(true);
            return role == UserRolesEnum.TEACHER
                    ? new TeachersCreateRes(usersRepo.save((Teachers) user))
                    : new StudentsCreateRes(usersRepo.save((Students) user));

        } else {
            throw new SecurityException("OTP is not correct");
        }

        // switch (role) {
        // case "TEACHER" -> {
        // Teachers teacher = usersRepo.findById(request.getId()).orElseThrow();
        // if (teacher.getOtp().equals(request.getOtp())) {
        // teacher.setOtp("");
        // teacher.setVerified(true);
        // return new TeachersCreateRes(usersRepo.save(teacher));

        // } else {
        // throw new SecurityException("OTP is not correct");
        // }

        // }
        // case "STUDENT" -> {
        // Students student = usersRepo.findById(request.getId()).orElseThrow();
        // if (student.getOtp().equals(request.getOtp())) {
        // student.setOtp("");
        // student.setVerified(true);
        // return new StudentsCreateRes(usersRepo.save((Students) student));

        // } else {
        // throw new SecurityException("OTP is not correct");
        // }
        // }
        // }
        // throw new UnsupportedOperationException("Invalid data provided");

    }

    public UserAccountResponse updateUser(String id, UserAccountRequest request) throws Exception {
        Users user;
        if (request instanceof TeachersRequest) {
            user = request.mapToTeachers((Teachers) usersRepo.findById(id).orElseThrow());

        } else {
            user = request.mapToStudents((Students) usersRepo.findById(id).orElseThrow());
        }

        if (request instanceof StudentsPassReq || request instanceof TeachersPassReq) {
            user.setPassword(passEncoder(user.getPassword()));
        }

        user.setUpdatedAt(Instant.now());

        
        return user.getRole().equals(UserRolesEnum.TEACHER.getRole())
                ? new TeachersCreateRes(usersRepo.save((Teachers) user))
                : new StudentsCreateRes(usersRepo.save((Students) user));

        // switch (role) {
        // case "TEACHER" -> {

        // Teachers teacher =
        // request.mapToTeachers(usersRepo.findById(id).orElseThrow());
        // if (request instanceof TeachersPassReq) {
        // teacher.setPassword(passEncoder(teacher.getPassword()));
        // }
        // return new TeachersCreateRes(usersRepo.save(teacher));
        // }
        // case "STUDENT" -> {

        // Students student =
        // request.mapToStudents(usersRepo.findById(id).orElseThrow());
        // if (request instanceof StudentsPassReq) {
        // student.setPassword(passEncoder(student.getPassword()));
        // }
        // return new StudentsCreateRes(usersRepo.save((Students) student));
        // }
        // }
        // throw new UnsupportedOperationException("Invalid data provided");

    }

    public void softDeleteUser(String id) throws IllegalArgumentException {
        usersRepo.deleteById(id);
    }

    public String passEncoder(String input) {
        return encoder.encode(input);

    }

    public void sendOtp(Users user) {

        String subject = "Account Verification for Keionbu user: " + user.getUsername();
        String body = "Your OTP for verification is: " + user.getOtp();
        try {
            mailService.sendEmail(user.getEmail(), subject, body);

        } catch (Exception e) {

        }

    }

    public String loginUser(LoginReq request) throws Exception {
        Users user = usersRepo.findByUsername(request.getUsername());

        if (encoder.matches(request.getPassword(), user.getPassword())) {
            return jwtService.generateToken(user);
        } else {
            return "Invalid credentials";
        }
    }


}
