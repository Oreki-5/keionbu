package com.oreki5.keionbu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Users;
import com.oreki5.keionbu.repositories.AssignmentsRepo;
import com.oreki5.keionbu.repositories.LessonsRepo;
import com.oreki5.keionbu.repositories.UsersRepo;

@Service
public class PreAuthService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AssignmentsRepo assignmentsRepo;

    @Autowired
    private LessonsRepo lessonsRepo;

    public boolean isOwner(String id) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users user = usersRepo.findByUsername(username);
        

        return user.getId().equals(id);
    }

    public boolean isAssignmentOwner(String id) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Assignments assignment = assignmentsRepo.findById(id).orElseGet(null);
        

        return assignment.getTeacher().getUsername().equals(username);
    }


    public boolean isAssignmentSubmitter(String id) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Assignments assignment = assignmentsRepo.findById(id).orElseGet(null);
        

        return assignment.getStudent().getUsername().equals(username);
    }
    public boolean isLessonOwner(String id) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Lessons lesson = lessonsRepo.findById(id).orElseGet(null);
        

        return lesson.getTeacher().getUsername().equals(username);
    }

}
