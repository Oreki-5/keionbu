package com.oreki5.keionbu.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsRequest;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsApprovalReq;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsApprovalRes;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsCreateReq;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsCreateRes;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsViewRes;
import com.oreki5.keionbu.dtoModels.lessons.LessonsCreateRes;
import com.oreki5.keionbu.dtoModels.lessons.LessonsViewRes;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsViewRes;
import com.oreki5.keionbu.repositories.AssignmentsRepo;
import com.oreki5.keionbu.repositories.LessonsRepo;
import com.oreki5.keionbu.repositories.UsersRepo;
import com.oreki5.keionbu.utils.StorageDirEnum;

import jakarta.activation.UnsupportedDataTypeException;

@Service
public class TeachersService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private LessonsRepo lessonsRepo;
    @Autowired
    private AssignmentsRepo assignmentsRepo;

    @Autowired
    private FileManagementService fileManagementService;
    /*
     * Browsing related
     */

    public List<StudentsResponse> getStudentsOfTeacher(String id) {
        // Teachers teacher = ;
        List<StudentsResponse> listOfStudents = new ArrayList<>();
        Teachers teacher = (Teachers) usersRepo.findById(id).orElseThrow();
        teacher.getStudents().forEach(student -> {
            listOfStudents.add((StudentsResponse) new StudentsViewRes(student));
        });

        return listOfStudents;
    }

    /*
     * Lessons Related
     */

    @Transactional
    public LessonsResponse createLesson(LessonsRequest request, String teacherId, MultipartFile lessonFile)
            throws UnsupportedDataTypeException, Exception {

        Lessons lesson = request.mapToLessons(new Lessons());
        lesson.setTeacher(usersRepo.findById(teacherId).orElseThrow());
        if (lessonsRepo.existsByLessonNoAndTeacher(lesson.getLessonNo(), lesson.getTeacher())) {
            throw new Exception("Duplicate");
        }
        if (lessonFile != null) {
            FileMetaData metadata = fileManagementService.uploadFile(lessonFile, StorageDirEnum.LESSON,
                    lesson.getTeacher().getId());
            lesson.setLessonFile(metadata);

        }
        lesson.setCreatedAt(Instant.now());

        lessonsRepo.save(lesson);
        return new LessonsCreateRes(lesson);
    }

    public List<LessonsResponse> getAllLessonsOfTeacher(String teacherId) {
        List<LessonsResponse> response = new ArrayList<>();
        List<Lessons> list = lessonsRepo.findAllByTeacher(usersRepo.findById(teacherId).orElseThrow());

        list.forEach((lesson) -> {
            response.add(new LessonsViewRes(lesson));
        });
        return response;
    }

    public LessonsResponse getLessonData(String id) {
        return new LessonsCreateRes(lessonsRepo.findById(id).orElseThrow());
    }

    public LessonsResponse updateLessonData(LessonsRequest request, String id, MultipartFile lessonFile)
            throws UnsupportedDataTypeException, Exception {
        Lessons lesson = request.mapToLessons(lessonsRepo.findById(id).orElseThrow());
        if (!lessonFile.getOriginalFilename().equals("")) {
            FileMetaData metadata = fileManagementService.uploadFile(lessonFile, StorageDirEnum.LESSON,
                    lesson.getTeacher().getId());
            lesson.setLessonFile(metadata);

        }
        // Later add verification for authorized teacher heres-> done
        lesson.setUpdatedAt(Instant.now());
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

    public AssignmentsResponse createAssignment(AssignmentsRequest request)
            throws UnsupportedDataTypeException, Exception {
        AssignmentsCreateReq typedRequest = (AssignmentsCreateReq) request;

        Students student = (Students) usersRepo.findById(typedRequest.getStudentId()).orElseThrow();
        Teachers teacher = (Teachers) usersRepo.findById(typedRequest.getTeacherId()).orElseThrow();
        Lessons lesson = lessonsRepo.findById(typedRequest.getLessonId()).orElseThrow();

        Assignments assignment = request.mapToAssignment(new Assignments());

        if (assignmentsRepo.existsByStudentAndTeacherAndLesson(student, teacher,
                lesson)) {
            throw new Exception("Duplicate");
        }
        assignment.setStudent(student);
        assignment.setTeacher(teacher);
        assignment.setLesson(lesson);
        assignment.setCreatedAt(Instant.now());
        return new AssignmentsCreateRes(assignmentsRepo.save(assignment));
    }

    public List<AssignmentsResponse> getAssignmentsWithFilters(String teacherId, String studentId, String status) {
        List<AssignmentsResponse> response = new ArrayList<>();
        if (studentId != null) {
            List<Assignments> list = assignmentsRepo.findAllByStudentAndTeacher(
                    usersRepo.findById(studentId).orElseThrow(), usersRepo.findById(teacherId).orElseThrow());
            list.forEach(item -> {
                response.add(new AssignmentsViewRes(item));
            });
        } else {
            List<Assignments> list = assignmentsRepo.findAllByTeacher(usersRepo.findById(teacherId).orElseThrow());
            list.forEach(item -> {
                response.add(new AssignmentsViewRes(item));
            });
        }

        return response;
    }

    public AssignmentsResponse editAssignment(AssignmentsRequest request, String id)
            throws UnsupportedDataTypeException, Exception {

        Assignments assignment = request.mapToAssignment(assignmentsRepo.findById(id).orElseThrow());

        if (request instanceof AssignmentsApprovalReq) {
            return new AssignmentsApprovalRes(assignmentsRepo.save(assignment));
        } else {
            AssignmentsCreateReq typedRequest = (AssignmentsCreateReq) request;
            assignment.setStudent(usersRepo.findById(typedRequest.getStudentId()).orElseThrow());
            assignment.setTeacher(usersRepo.findById(typedRequest.getTeacherId()).orElseThrow());
            assignment.setLesson(lessonsRepo.findById(typedRequest.getLessonId()).orElseThrow());
            assignment.setUpdatedAt(Instant.now());
            return new AssignmentsCreateRes(assignmentsRepo.save(assignment));

        }
    }

    @Transactional
    public void deleteAssignment(String id) throws Exception {

        if (!assignmentsRepo.existsById(id)) {
            throw new Exception("record doesnt exist");
        }
        assignmentsRepo.deleteById(id);
    }

}
