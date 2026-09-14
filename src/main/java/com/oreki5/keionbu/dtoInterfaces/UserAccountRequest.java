package com.oreki5.keionbu.dtoInterfaces;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;

public interface UserAccountRequest {
    Users mapToTeachers(Teachers teacher) throws Exception;

    Users mapToStudents(Students student) throws Exception;

}
