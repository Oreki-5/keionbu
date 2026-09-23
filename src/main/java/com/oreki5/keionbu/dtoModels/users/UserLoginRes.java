package com.oreki5.keionbu.dtoModels.users;

import java.time.Instant;

import com.oreki5.keionbu.dbEntities.Users;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

import lombok.Data;

@Data
public class UserLoginRes implements UserAccountResponse{
    private String id;
    private String firstName;
    private String lastName;
    private String subject;
    private String role;
    private String email;
    private String username;
    private Instant createdAt;
    private Instant updatedAt;
    private String token;

    
    public UserLoginRes(Users user) {
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
        role = user.getRole();
    }
}
