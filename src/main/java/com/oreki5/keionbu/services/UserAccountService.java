package com.oreki5.keionbu.services;

import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dtoInterfaces.UserAccountRequest;
import com.oreki5.keionbu.dtoInterfaces.UserAccountResponse;

@Service
public class UserAccountService {

    public UserAccountResponse createUser(UserAccountRequest request){
        return null;
    }


    public UserAccountResponse updateUser(String id, UserAccountRequest request){
        return null;
    }

    public UserAccountResponse updatePassword(String id, UserAccountRequest request){
        return null;
    }

    public void softDeleteUser(String id){
        
    }

    

    
}
