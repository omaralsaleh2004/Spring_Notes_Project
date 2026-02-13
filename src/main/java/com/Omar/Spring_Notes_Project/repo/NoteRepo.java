package com.Omar.Spring_Notes_Project.repo;

import com.Omar.Spring_Notes_Project.model.Note;
import com.Omar.Spring_Notes_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {
    List<Note> findByUser (User user);
    Optional<Note> findById (Integer id);
    void deleteByUser (User user);
}
