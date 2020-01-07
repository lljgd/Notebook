package com.example.notebook.data.store;

import android.content.Context;

import com.example.notebook.data.room.RoomNoteStore;

public class NoteStoreProvider {

    private static NoteStore instance;

    public static NoteStore getInstance(Context context) {
        if (instance == null) {
            instance = new RoomNoteStore(context);
        }
        return instance;
    }
}
