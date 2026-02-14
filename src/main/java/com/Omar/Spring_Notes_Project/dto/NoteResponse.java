package com.Omar.Spring_Notes_Project.dto;

import java.time.LocalDate;
import java.util.List;

public record NoteResponse(
        Integer id,
        String title,
        String description,
        LocalDate createdAt,
        boolean isCompleted) {
}
