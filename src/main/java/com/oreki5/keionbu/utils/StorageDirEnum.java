package com.oreki5.keionbu.utils;

import java.io.File;

public enum StorageDirEnum {
    LESSON(File.separator + "lessons"),
    SUBMISSION(File.separator + "submissions");

    private final String path;

    StorageDirEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
