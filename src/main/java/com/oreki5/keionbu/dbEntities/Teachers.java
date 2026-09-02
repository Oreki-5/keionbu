package com.oreki5.keionbu.dbEntities;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(collection="teachers")
@Data
@EqualsAndHashCode(callSuper=false)
public class Teachers extends Users{

    private String firstName; 
    private String lastName;

    private String subject;
    private List<Students> students;

    
}
