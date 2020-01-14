package com.example.notebook.data.store;



import com.example.notebook.data.model.Note;

import java.util.List;
import java.util.UUID;

public interface NoteStore {
    List<Note> getAllNotes();

    Note getById(UUID id);

    int getNumber();

    void deleteNote(Note note);

    void insert(Note note);

    void addListener(Listener listener);

    void removeListener(Listener listener);

    interface Listener {
        void onNotesListChanged();
    }
}
