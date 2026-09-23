package com.oreki5.keionbu.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oreki5.keionbu.services.FileManagementService;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin
public class FileAccessController {

    @Autowired
    private FileManagementService fileManagementService;

    @GetMapping("/assignments/{id}")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER') and @preAuthService.isAssignmentLessonAccessible(#id)")
    public ResponseEntity<?> getLessonFile(@PathVariable String id) {
        try {
            return fileManagementService.getAssignmentLessonFile(id);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("File doesn't exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<?> getSubmissionFile(@PathVariable String id) {
        try {
            return fileManagementService.getSubmmissionFile(id);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("File doesn't exists", HttpStatus.BAD_REQUEST);
        }
    }

}