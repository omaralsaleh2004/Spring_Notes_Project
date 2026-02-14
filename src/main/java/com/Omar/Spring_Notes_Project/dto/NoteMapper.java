package com.Omar.Spring_Notes_Project.dto;

import com.Omar.Spring_Notes_Project.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteResponse toResponse (Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.getCreatedAt(),
                note.isCompleted()
        );
    }
}
