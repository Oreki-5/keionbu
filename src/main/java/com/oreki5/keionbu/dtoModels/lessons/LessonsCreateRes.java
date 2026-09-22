package com.oreki5.keionbu.dtoModels.lessons;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;

import lombok.Data;

@Data
public class LessonsCreateRes implements LessonsResponse {
    private String id;
    private String teacherId;
    private String teacherName;
    private long lessonNo;
    private String lessonName;
    private String lessonDesc;
    private List<String> objectives = new ArrayList<>();
    private String lessonFile;
    private String lessonDifficulty;
    private long requiredScore;
    private Instant createdAt;
    private Instant updatedAt;

    public LessonsCreateRes(Lessons lesson) {
        id = lesson.getId();
        teacherId = lesson.getTeacher().getId();
        teacherName = lesson.getTeacher().getUsername();
        lessonNo = lesson.getLessonNo();
        lessonName = lesson.getLessonName();
        lessonDesc = lesson.getLessonDesc();
        objectives = lesson.getObjectives();
        lessonDifficulty = lesson.getLessonDifficulty();
        requiredScore = lesson.getRequiredScore();
        lessonFile = lesson.getLessonFile().originalName();
        createdAt = lesson.getCreatedAt();
        updatedAt = lesson.getUpdatedAt();
    }
}
