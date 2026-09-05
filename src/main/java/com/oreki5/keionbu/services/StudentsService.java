package com.oreki5.keionbu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.oreki5.keionbu.dbEntities.Assignments;
import com.oreki5.keionbu.dbEntities.FileMetaData;
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsCreateRes;
import com.oreki5.keionbu.dtoModels.students.StudentsJoinReq;
import com.oreki5.keionbu.dtoModels.teachers.TeachersViewRes;
import com.oreki5.keionbu.repositories.AssignmentsRepo;
import com.oreki5.keionbu.repositories.StudentsRepo;
import com.oreki5.keionbu.repositories.TeachersRepo;
import com.oreki5.keionbu.utils.StorageDirEnum;

@Service
public class StudentsService {

    @Autowired
    private TeachersRepo teachersRepo;
    @Autowired
    private StudentsRepo studentsRepo;
    @Autowired
    private AssignmentsRepo assignmentsRepo;

    @Autowired
    private FileManagementService fileManagementService;
    /*
     * Teacher joining related
     */

    public List<TeachersResponse> getAllTeachers() {
        List<Teachers> list = teachersRepo.findAll();
        List<TeachersResponse> response = new ArrayList<>();
        list.forEach(teacher -> {
            response.add(new TeachersViewRes(teacher));
        });
        return response;
    }

    public List<TeachersResponse> getJoinedTeachers(String id) {
        List<Teachers> list = studentsRepo.findById(id).get().getTeachersList();
        List<TeachersResponse> response = new ArrayList<>();
        list.forEach(teacher -> {
            response.add(new TeachersViewRes(teacher));
        });
        return response;

    }

    @Transactional
    public StudentsResponse joinTeacher(StudentsJoinReq request) throws Exception {

        Students student = studentsRepo.findById(request.getId()).orElseThrow();
        Teachers teacher = teachersRepo.findById(request.getTeacherId()).orElseThrow();

        List<Teachers> exisitingList = student.getTeachersList();
        if (exisitingList.contains(teacher)) {
            throw new Exception("Already joined the teacher!");
        }
        exisitingList.add(teacher);
        student.setTeachersList(exisitingList);

        List<Students> enrolledStudents = teacher.getStudents();
        enrolledStudents.add(student);
        teacher.setStudents(enrolledStudents);

        teachersRepo.save(teacher);

        // verify the correct response
        return new StudentsCreateRes(studentsRepo.save(student));
    }

    /*
     * Assignments related
     */

    public List<AssignmentsResponse> getAssignmentsOfStudent(String studentId, String teacherId) {

        if (teacherId.isEmpty()) {
            List<AssignmentsResponse> list = assignmentsRepo
                    .findAllByStudent(studentsRepo.findById(studentId).orElseThrow());
            return list;
        }
        List<AssignmentsResponse> list = assignmentsRepo
                .findAllByStudentAndTeacher(studentsRepo.findById(studentId).orElseThrow(),
                        teachersRepo.findById(teacherId).orElseThrow());
        return null;
    }

    @Transactional
    public AssignmentsResponse submitAssignment(String assignmentId, MultipartFile submissionFile)
            throws Exception {

        if (!assignmentsRepo.existsById(assignmentId)) {
            throw new Exception("Assignment doesnt exist");
        }
        Assignments assignment = assignmentsRepo.findById(assignmentId).get();
        FileMetaData metadata = fileManagementService.uploadFile(submissionFile, StorageDirEnum.SUBMISSION,
                assignmentId);

        assignment.setSubmission(metadata);

        // Need to chanage response model here
        return new AssignmentsCreateRes(assignment);
    }
}
