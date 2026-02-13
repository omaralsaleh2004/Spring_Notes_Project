package com.Omar.Spring_Notes_Project.service;


import com.Omar.Spring_Notes_Project.model.User;
import com.Omar.Spring_Notes_Project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User getUserByEmail(String email) {
       return userRepo.findByEmail(email);
    }
}
