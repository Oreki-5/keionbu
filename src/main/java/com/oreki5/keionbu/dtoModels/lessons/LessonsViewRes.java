package com.oreki5.keionbu.dtoModels.lessons;

import com.oreki5.keionbu.dbEntities.Lessons;

public class LessonsViewRes {
    private String id;
    private long lessonNo;
    private String lessonName;

    public LessonsViewRes(Lessons lesson) {
        id = lesson.getId();
        lessonNo = lesson.getLessonNo();
        lessonName = lesson.getLessonName();
    }
}
