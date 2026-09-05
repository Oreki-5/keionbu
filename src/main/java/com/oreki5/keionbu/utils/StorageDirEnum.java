package com.oreki5.keionbu.utils;

import java.io.File;

public enum StorageDirEnum {
    LESSON(File.separator + "resumes"),
    SUBMISSION(File.separator + "photos");

    private final String path;

    StorageDirEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
