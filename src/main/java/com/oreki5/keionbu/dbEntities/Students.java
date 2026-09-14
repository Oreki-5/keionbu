package com.oreki5.keionbu.dbEntities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;
import lombok.EqualsAndHashCode;

// @Document(collection="students")
@Data
@EqualsAndHashCode(callSuper=false)
public class Students extends Users {
    @DBRef(lazy=true)
    private List<Teachers> teachersList = new ArrayList<>();
    

}
