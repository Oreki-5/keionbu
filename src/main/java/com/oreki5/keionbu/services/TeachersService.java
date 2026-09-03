package com.oreki5.keionbu.services;

import com.oreki5.keionbu.repositories.LessonsRepo;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoModels.lessons.LessonsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.repositories.StudentsRepo;
import com.oreki5.keionbu.repositories.TeachersRepo;

import jakarta.activation.UnsupportedDataTypeException;

@Service
public class TeachersService {

    @Autowired
    private TeachersRepo teachersRepo;
    @Autowired
    private StudentsRepo studentsRepo;
    @Autowired
    private LessonsRepo lessonsRepo;
    /*
     * Browsing related
     */

    public List<StudentsResponse> getStudentsOfTeacher(String id) {
        // Teachers teacher = ;
        List<StudentsResponse> listOfStudents = new ArrayList<>();
        teachersRepo.findById(id).orElseThrow().getStudents().forEach(student -> {
            listOfStudents.add((StudentsResponse) new StudentsCreateRes(student));
        });

        return listOfStudents;
    }

    /*
     * Lessons Related
     */

    public LessonsResponse createLesson(LessonsRequest request, String teacherId)
            throws UnsupportedDataTypeException, Exception {
        Lessons lesson = request.mapToLessons(new Lessons());
        lesson.setTeacher(teachersRepo.findById(teacherId).orElseThrow());
        if (lessonsRepo.existsByLessonNoAndTeacher(lesson.getLessonNo(), lesson.getTeacher())) {
            throw new Exception("Duplicate");
        }
        return new LessonsCreateRes(lessonsRepo.save(lesson));
    }

    public List<LessonsResponse> getLessonData(String id) {
        return null;
    }

    public LessonsResponse updateLessonData(LessonsRequest request, String id)
            throws UnsupportedDataTypeException, Exception {
        Lessons lesson = request.mapToLessons(lessonsRepo.findById(id).orElseThrow());
        // Later add verification for authorized teacher heres
        return new LessonsCreateRes(lessonsRepo.save(lesson));
    }

    public void softDeleteLesson(String id) throws NullPointerException {
        if (!lessonsRepo.existsById(id))
            throw new NullPointerException("record with id doesn't exists");
        lessonsRepo.deleteById(id);
    }

    /*
     * Assignment Related
     */

    public AssignmentsResponse createAssignment(AssignmentsRequest request) {
        return null;
    }

    public List<AssignmentsResponse> getAssignmentsWithFilters(String studentId) {
        return null;
    }

    public AssignmentsResponse editAssignment(AssignmentsRequest request) {
        return null;
    }

    public AssignmentsResponse approveAssignment(AssignmentsRequest request) {
        return null;
    }

    public void deleteAssignment(String id) {

    }

}
