package com.oreki5.keionbu.utils;

public enum UserRolesEnum {
    TEACHER("ROLE_TEACHER"),
    STUDENT("ROLE_STUDENT");

    private String role;

    UserRolesEnum(String role) {
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
