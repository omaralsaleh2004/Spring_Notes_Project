package com.Omar.Spring_Notes_Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String title;
    private String description;
    @CreatedDate
    private LocalDate createdAt;
    private boolean isCompleted = false;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
