package com.Omar.Spring_Notes_Project.controller;


import com.Omar.Spring_Notes_Project.dto.NoteResponse;
import com.Omar.Spring_Notes_Project.dto.UpdateNoteRequest;
import com.Omar.Spring_Notes_Project.model.Note;
import com.Omar.Spring_Notes_Project.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;


    //create Note
    @PostMapping("/note")
    public ResponseEntity<NoteResponse> addNote (@RequestBody Note note) {
            return ResponseEntity.ok(noteService.addNote(note));
    }


    //get My Notes
    @GetMapping("/notes")
    public ResponseEntity<List<NoteResponse>> getMyNotes () {
        return ResponseEntity.ok(noteService.getMyNotes());
    }


    //get specific Note
    @GetMapping("/note/{noteId}")
    public ResponseEntity<?> getNote (@PathVariable int noteId) {
            return ResponseEntity.ok(noteService.getNote(noteId));
    }

    //delete Note
    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<?> deleteNote (@PathVariable int noteId) {
            noteService.deleteNote(noteId);
            return ResponseEntity.ok().body(Map.of("message" , "Note Deleted Successfully"));
    }

    //delete all notes for specific user
    @DeleteMapping("/note")
    public ResponseEntity<?> deleteNotes () {
         noteService.deleteNotes();
         return ResponseEntity.ok().body(Map.of("message","All notes Deleted Successfully"));
    }

    //edit Note
    @PutMapping("/note/{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteRequest request, @PathVariable int noteId) {
            noteService.updateNote(request, noteId);
            return ResponseEntity.ok().body(Map.of("message" , "note updated Successfully"));
    }

    @GetMapping("/note/search")
    public ResponseEntity<List<NoteResponse>> searchByKeyword(@RequestParam String keyword) {
        return noteService.searchByKeyword(keyword);
    }
}
