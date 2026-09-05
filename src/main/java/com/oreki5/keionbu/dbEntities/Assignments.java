package com.oreki5.keionbu.dbEntities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Assignments extends BaseEntity{

    private Students student;
    private Teachers teacher;
    private Lessons lesson;
    private FileMetaData submission;
    private String approvalStatus;
    private int score;
    private String comment;

}
