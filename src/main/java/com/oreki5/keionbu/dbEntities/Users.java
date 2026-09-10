package com.oreki5.keionbu.dbEntities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(collection="users")
@Data
@EqualsAndHashCode(callSuper=false)
public class Users extends BaseEntity {
    private String username;
    private String password;
    private String firstName; 
    private String lastName;
    private String email;
    private String otp;
    private boolean verified;
    private String role;
}
