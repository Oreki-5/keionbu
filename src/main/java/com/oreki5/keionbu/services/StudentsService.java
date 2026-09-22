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
import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;
import com.oreki5.keionbu.dtoInterfaces.AssignmentsResponse;
import com.oreki5.keionbu.dtoInterfaces.StudentsResponse;
import com.oreki5.keionbu.dtoInterfaces.TeachersResponse;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsCreateRes;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsSubmitRes;
import com.oreki5.keionbu.dtoModels.assignments.AssignmentsViewRes;
import com.oreki5.keionbu.dtoModels.students.StudentsJoinReq;
import com.oreki5.keionbu.dtoModels.students.StudentsJoinRes;
import com.oreki5.keionbu.dtoModels.teachers.TeachersViewRes;
import com.oreki5.keionbu.repositories.AssignmentsRepo;
import com.oreki5.keionbu.repositories.UsersRepo;
import com.oreki5.keionbu.utils.StorageDirEnum;

@Service
public class StudentsService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private AssignmentsRepo assignmentsRepo;

    @Autowired
    private FileManagementService fileManagementService;
    /*
     * Teacher joining related
     */

    public List<TeachersResponse> getAllTeachers() {
        List<Users> list = usersRepo.findAllTeachers();
        List<TeachersResponse> response = new ArrayList<>();
        list.forEach(teacher -> {
            response.add(new TeachersViewRes((Teachers) teacher));
        });
        return response;
    }

    public List<TeachersResponse> getJoinedTeachers(String id) throws Exception {
        Students student = (Students) usersRepo.findById(id).orElseThrow();
        List<Teachers> list = student.getTeachersList();
        List<TeachersResponse> response = new ArrayList<>();
        list.forEach(teacher -> {
            response.add(new TeachersViewRes(teacher));
        });
        return response;

    }

    @Transactional
    public StudentsResponse joinTeacher(StudentsJoinReq request) throws Exception {

        Students student = (Students) usersRepo.findById(request.getId()).orElseThrow();
        Teachers teacher = (Teachers) usersRepo.findById(request.getTeacherId()).orElseThrow();

        List<Teachers> exisitingList = student.getTeachersList();
        if (exisitingList.contains(teacher)) {
            throw new Exception("Already joined the teacher!");
        }
        exisitingList.add(teacher);
        student.setTeachersList(exisitingList);

        List<Students> enrolledStudents = teacher.getStudents();
        enrolledStudents.add(student);
        teacher.setStudents(enrolledStudents);

        teacher.setUpdatedAt(Instant.now());
        student.setUpdatedAt(Instant.now());

        usersRepo.save(teacher);

        // verify the correct response
        return new StudentsJoinRes(usersRepo.save((Students) student));
    }

    @Transactional
    public StudentsResponse leaveTeacher(StudentsJoinReq request) throws Exception {

        Students student = (Students) usersRepo.findById(request.getId()).orElseThrow();
        Teachers teacher = (Teachers) usersRepo.findById(request.getTeacherId()).orElseThrow();

        List<Teachers> exisitingList = student.getTeachersList();
        exisitingList.remove(teacher);
        student.setTeachersList(exisitingList);

        List<Students> enrolledStudents = teacher.getStudents();
        enrolledStudents.remove(student);
        teacher.setStudents(enrolledStudents);

        teacher.setUpdatedAt(Instant.now());
        student.setUpdatedAt(Instant.now());

        usersRepo.save(teacher);

        // verify the correct response
        return new StudentsJoinRes(usersRepo.save(student));
    }

    /*
     * Assignments related
     */

    public List<AssignmentsResponse> getAssignmentsOfStudent(String studentId, String teacherId) {
        List<Assignments> list;
        List<AssignmentsResponse> response = new ArrayList<>();
        if (teacherId == null) {
            // Students student = (Students) usersRepo.findById(studentId).orElseThrow();
            list = assignmentsRepo.findAllByStudent(usersRepo.findById(studentId).orElseThrow());
            list.forEach(assignment -> {
                response.add(new AssignmentsViewRes(assignment));
            });

        } else {
            list = assignmentsRepo
                    .findAllByStudentAndTeacher(usersRepo.findById(studentId).orElseThrow(),
                            usersRepo.findById(teacherId).orElseThrow());
            list.forEach(assignment -> {
                response.add(new AssignmentsViewRes(assignment));
            });
        }

        return response;
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
        assignment.setApprovalStatus("submitted");

        assignment.setUpdatedAt(Instant.now());
        // Need to chanage response model here
        return new AssignmentsSubmitRes(assignmentsRepo.save(assignment));
    }
}
