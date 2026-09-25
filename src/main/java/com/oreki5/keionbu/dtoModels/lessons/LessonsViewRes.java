package com.oreki5.keionbu.dtoModels.lessons;

import com.oreki5.keionbu.dbEntities.Lessons;
import com.oreki5.keionbu.dtoInterfaces.LessonsResponse;

import lombok.Data;
@Data 
public class LessonsViewRes implements LessonsResponse {
    private String id;
    private long lessonNo;
    private String lessonName;

    public LessonsViewRes(Lessons lesson) {
        id = lesson.getId();
        lessonNo = lesson.getLessonNo();
        lessonName = lesson.getLessonName();
    }
}
