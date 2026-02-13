package com.Omar.Spring_Notes_Project.repo;


import com.Omar.Spring_Notes_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail (String email);
}
