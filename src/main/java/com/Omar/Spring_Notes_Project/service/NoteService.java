package com.Omar.Spring_Notes_Project.service;


import com.Omar.Spring_Notes_Project.dto.NoteResponse;
import com.Omar.Spring_Notes_Project.dto.UpdateNoteRequest;
import com.Omar.Spring_Notes_Project.exception.InvalidDataException;
import com.Omar.Spring_Notes_Project.exception.NotFoundException;
import com.Omar.Spring_Notes_Project.exception.UnauthorizedException;
import com.Omar.Spring_Notes_Project.model.Note;
import com.Omar.Spring_Notes_Project.model.User;
import com.Omar.Spring_Notes_Project.model.UserPrinciple;
import com.Omar.Spring_Notes_Project.repo.NoteRepo;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepo noteRepo;

    @Autowired
    AuthService authService;

    public NoteResponse addNote(Note note) {
        User user = authService.getCurrentUser();
        if(user == null) {
            throw new UnauthorizedException("Unauthorized to add Note");
        }
        if(note.getTitle() == null || note.getDescription() == null){
            throw new InvalidDataException("Please Enter title and description");
        }
        note.setUser(user);
        noteRepo.save(note);
       return new NoteResponse(note.getId() , note.getTitle() , note.getDescription());
    }

    public List<NoteResponse> getMyNotes () {
        User user = authService.getCurrentUser();
        if(user == null) {
            throw new UnauthorizedException("Unauthorized to add Note");
        }
        List<Note> notes = noteRepo.findByUser(user);
        return notes.stream().map(n -> new NoteResponse(n.getId() , n.getTitle() , n. getDescription())).toList();
    }


    public void deleteNote(int noteId) {
       User user = authService.getCurrentUser();
        if(user == null) {
            throw new UnauthorizedException("Unauthorized to add Note");
        }
       Note note = noteRepo.findById(noteId).orElseThrow(() -> new NotFoundException("Note Not found"));
        if (!note.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this note");
        }
        noteRepo.deleteById(noteId);
    }


    public @Nullable NoteResponse getNote(int noteId) {
        User user = authService.getCurrentUser();
        if(user == null) {
            throw new UnauthorizedException("Unauthorized to add Note");
        }
        Note note = noteRepo.findById(noteId).orElseThrow(() -> new NotFoundException("Note Not found"));
        if (!note.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to see this note");
        }
        return new NoteResponse(note.getId() ,note.getTitle() , note.getDescription());
    }

    @Transactional
    public void deleteNotes() {
        User user = authService.getCurrentUser();
        if(user == null) {
            throw new UnauthorizedException("UnAuthorized");
        }
        List<Note> notes = noteRepo.findByUser(user);

        if(notes.isEmpty()) {
            throw new NotFoundException("No notes found for "+user.getFirstName());
        }
        noteRepo.deleteByUser(user);

    }

    public void updateNote(UpdateNoteRequest request, int noteId) {
        User user = authService.getCurrentUser();

        if(user == null) {
            throw new UnauthorizedException("UnAuthorized");
        }
        Note note = noteRepo.findById(noteId).orElseThrow(() -> new NotFoundException("Note Not Found"));

        if(!note.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("your are Not Authorized to edit this note");
        }

        if(request.title() != null) {
            note.setTitle(request.title());
        }

        if(request.description() != null) {
            note.setDescription(request.description());
        }

        noteRepo.save(note);
    }


    public ResponseEntity<List<NoteResponse>> searchByKeyword(String keyword) {
        User user = authService.getCurrentUser();
        if(user == null) {
            throw new UnauthorizedException("UnAuthorized");
        }
        List<Note> notes = noteRepo.findByUser(user);

        if(notes.isEmpty()) {
            throw new NotFoundException("No Notes Found");
        }
       List<NoteResponse> filtered = notes.stream()
               .filter(note -> note.getTitle().toLowerCase().contains(keyword.toLowerCase()) || note.getDescription().toLowerCase().contains(keyword.toLowerCase()))
               .map(note -> new NoteResponse(note.getId() , note.getTitle(),note.getDescription()))
               .toList();

        if(filtered.isEmpty()) {
            throw new NotFoundException("No Notes Match The Keyword");
        }
        return ResponseEntity.ok().body(filtered);
    }
}
