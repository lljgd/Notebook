package com.example.notebook.data.room;

import android.content.Context;

import androidx.room.Room;

import com.example.notebook.data.model.Note;
import com.example.notebook.data.store.BaseNoteStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomNoteStore extends BaseNoteStore {

    private NoteDao noteDao;

    public RoomNoteStore(Context context) {
        noteDao = Room
                .databaseBuilder(context, NoteDatabase.class, "note-database")
                .allowMainThreadQueries()
                .build()
                .noteDao();
    }

    @Override
    public List<Note> getAllNotes() {
        List<NoteEntity> notesEntityList = noteDao.getAllNotes();

        List<Note> notesResult = new ArrayList<>();

        for (NoteEntity noteEntity: notesEntityList) {
            notesResult.add(Converter.convert(noteEntity));
        }
        return notesResult;
    }

    @Override
    public void deleteNote(Note note) {
        noteDao.delete(Converter.convert(note));
        notifyListeners();
    }

    @Override
    public Note getById(UUID id) {
        NoteEntity noteEntity = noteDao.getNoteById(id.toString());

        return Converter.convert(noteEntity);
    }

    @Override
    public int getNumber() {
        return Integer.parseInt(noteDao.getNumber()) + 1;
    }

    /*@Override
    public void update(Note note) {
        noteDao.update(Converter.convert(note));
    }*/

    @Override
    public void insert(Note note) {
        noteDao.insert(Converter.convert(note));
        notifyListeners();
    }
}
