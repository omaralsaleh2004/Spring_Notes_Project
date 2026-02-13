package com.Omar.Spring_Notes_Project.service;


import com.Omar.Spring_Notes_Project.model.User;
import com.Omar.Spring_Notes_Project.model.UserPrinciple;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public User getCurrentUser() {
        UserPrinciple principle = (UserPrinciple) SecurityContextHolder
                                    .getContext()
                                    .getAuthentication()
                                    .getPrincipal();
        return principle.getUser();
    }
}
