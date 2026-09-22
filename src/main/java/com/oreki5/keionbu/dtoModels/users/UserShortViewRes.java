package com.oreki5.keionbu.dtoModels.users;

import com.oreki5.keionbu.dbEntities.Students;
import com.oreki5.keionbu.dbEntities.Teachers;
import com.oreki5.keionbu.dbEntities.Users;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

public class UserShortViewRes implements UserAccountResponse {

    private String id;
    private String username;

    public UserShortViewRes(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

}
