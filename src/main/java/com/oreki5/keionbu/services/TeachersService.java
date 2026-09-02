package com.oreki5.keionbu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;

@Service
public class TeachersService {

    /*
        Browsing related
    */


        
    public List<StudentsResponse> getStudentsOfTeacher(String id) {
        return null;
    }

    /*
     * Lessons Related
     */

    public LessonsResponse createLesson(LessonsRequest request) {
        return null;
    }

    public List<LessonsResponse> getLessonData(String id) {
        return null;
    }

    public LessonsResponse updateLessonData(LessonsRequest request) {

        return null;
    }

    public void softDeleteLesson(String id) {

    }

    /*
     * Assignment Related
     */

    public AssignmentsResponse createAssignment(AssignmentsRequest request){
        return null;
    }

    public List<AssignmentsResponse> getAssignmentsWithFilters(String studentId){
        return null;
    }

    public AssignmentsResponse editAssignment(AssignmentsRequest request){
        return null;
    }

    public AssignmentsResponse approveAssignment(AssignmentsRequest request){
        return null;
    }
    
    public void deleteAssignment(String id){

    }

}
