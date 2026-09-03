package com.oreki5.keionbu.dtoModels.lessons;

import java.util.ArrayList;
import java.util.List;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;
import lombok.Data;

@Data
public class LessonsCreateRes implements LessonsResponse {
    private long lessonNo;
    private String lessonName;
    private String lessonDesc;
    private List<String> objectives = new ArrayList<>();
    // lesson file file column
    private String lessonDifficulty;
    private long requiredScore;

    public LessonsCreateRes(Lessons lesson){

    }
}
