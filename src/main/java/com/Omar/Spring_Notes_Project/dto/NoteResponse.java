package com.Omar.Spring_Notes_Project.dto;

import java.util.List;

public record NoteResponse(
         Integer id,
         String title,
         String description) {
}
