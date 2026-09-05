package com.oreki5.keionbu.dbEntities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(collection="lessons")
@Data
@EqualsAndHashCode(callSuper = false)
public class Lessons extends BaseEntity {
    @DBRef
    private Teachers teacher;
    private long lessonNo;
    private String lessonName;
    private String lessonDesc;
    private List<String> objectives = new ArrayList<>();

    @DBRef
    private FileMetaData lessonFile;
    private String lessonDifficulty;
    private long requiredScore;

}
