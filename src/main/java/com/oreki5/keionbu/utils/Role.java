package com.oreki5.keionbu.utils;

import java.util.Set;

public enum Role {
    TEACHER("ROLE_TEACHER", Set.of(Permissions.LESSON_READ, Permissions.LESSON_WRITE, Permissions.ASSIGNMENT_READ,
            Permissions.ASSIGNMENT_WRITE)),
    STUDENT("ROLE_STUDENT", Set.of(Permissions.LESSON_READ, Permissions.LESSON_WRITE, Permissions.ASSIGNMENT_READ,
            Permissions.ASSIGNMENT_WRITE));

    private Set<Permissions> permissions;
    private String role;

    private Role(String role, Set<Permissions> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public String getRole() {
        return this.role;
    }

    public Set<Permissions> getPermissions() {
        return this.permissions;
    }

}
