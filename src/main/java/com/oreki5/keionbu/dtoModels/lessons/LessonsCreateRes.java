package com.oreki5.keionbu.dtoModels.lessons;

import java.util.ArrayList;
import java.util.List;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;

import lombok.Data;

@Data
public class LessonsCreateRes implements LessonsResponse {
    private String id;
    private long lessonNo;
    private String lessonName;
    private String lessonDesc;
    private List<String> objectives = new ArrayList<>();
    private String lessonFile;
    private String lessonDifficulty;
    private long requiredScore;

    public LessonsCreateRes(Lessons lesson) {
        id = lesson.getId();
        lessonNo = lesson.getLessonNo();
        lessonName = lesson.getLessonName();
        lessonDesc = lesson.getLessonDesc();
        objectives = lesson.getObjectives();
        lessonDifficulty = lesson.getLessonDifficulty();
        requiredScore = lesson.getRequiredScore();
        lessonFile = lesson.getLessonFile().originalName();
    }
}
