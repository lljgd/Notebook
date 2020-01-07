package com.example.notebook.data.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteEntity {

    @PrimaryKey
    @NonNull
    public String id;
    public String record;
    public long data;
    public boolean done;
    public String topic;
    public int number;
}
