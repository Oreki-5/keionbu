package com.oreki5.keionbu.dbEntities;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(collection="students")
@Data
@EqualsAndHashCode(callSuper=false)
public class Students extends Users {
    private String firstName;
    private String lastName;
    private List<Teachers> teachers = new ArrayList<>();
}
