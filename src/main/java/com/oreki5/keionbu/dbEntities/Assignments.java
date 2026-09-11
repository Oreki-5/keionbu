package com.oreki5.keionbu.dbEntities;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Assignments extends BaseEntity{

    @DBRef 
    private Users student;
    @DBRef
    private Users teacher;
    @DBRef
    private Lessons lesson;
    @DBRef 
    private FileMetaData submission;
    private String approvalStatus;
    private int score;
    private String comment;

}
