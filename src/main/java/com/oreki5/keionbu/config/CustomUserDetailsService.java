// package com.oreki5.keionbu.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.oreki5.keionbu.dbEntities.Users;
// import com.oreki5.keionbu.repositories.StudentsRepo;
// import com.oreki5.keionbu.repositories.TeachersRepo;

// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired 
//     private TeachersRepo teachersRepo;
    
//     @Autowired 
//     private StudentsRepo studentsRepo;


//     @Override
//     public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         return new CustomUserDetails(new Users());
//     }

// }
