package com.example.notebook.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    List<NoteEntity> getAllNotes();

    @Query("SELECT * FROM NoteEntity WHERE id == :id")
    NoteEntity getNoteById(String id);

    @Insert
    void insert(NoteEntity noteEntity);

    @Delete
    void delete(NoteEntity noteEntity);

    @Update
    void update(NoteEntity noteEntity);
}
