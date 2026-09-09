package com.oreki5.keionbu.dbEntities;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Users extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String otp;
    private boolean verified;
}
