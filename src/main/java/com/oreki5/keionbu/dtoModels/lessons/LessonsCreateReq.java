package com.oreki5.keionbu.dtoModels.lessons;

import java.util.ArrayList;
import java.util.List;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dtoInterfaces.LessonsRequest;

import jakarta.activation.UnsupportedDataTypeException;
import lombok.Data;

@Data
public class LessonsCreateReq implements LessonsRequest {

    private long lessonNo;
    private String lessonName;
    private String lessonDesc;
    private List<String> objectives = new ArrayList<>();
    private String lessonDifficulty;
    private long requiredScore;

    @Override
    public Lessons mapToLessons(Lessons lesson) throws UnsupportedDataTypeException {

        lesson.setLessonNo(lessonNo);
        lesson.setLessonName(lessonName);
        lesson.setLessonDesc(lessonDesc);
        lesson.setObjectives(objectives);
        lesson.setLessonDifficulty(lessonDifficulty);
        lesson.setRequiredScore(requiredScore);

        return lesson;
    }
}
