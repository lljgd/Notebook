package com.example.notebook.data.store;

import com.example.notebook.data.model.Note;

import java.util.List;

public interface NoteStore {

    List<Note> getAllNotes();

    void deleteNote(Note note);

    void insert(Note note);
}
