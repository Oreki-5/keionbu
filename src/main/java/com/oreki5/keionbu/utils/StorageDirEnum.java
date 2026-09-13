package com.oreki5.keionbu.utils;

public enum StorageDirEnum {
    LESSON("lessons"),
    SUBMISSION("submissions");

    private final String path;

    StorageDirEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
