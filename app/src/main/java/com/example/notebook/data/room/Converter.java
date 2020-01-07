package com.example.notebook.data.room;

import com.example.notebook.data.model.Note;

import java.util.Date;
import java.util.UUID;

class Converter {
    static Note convert(NoteEntity entity) {
        Note note = new Note();

        note.setId(UUID.fromString(entity.id));
        note.setRecord(entity.record);
        note.setData(new Date(entity.data));
        note.setDone(entity.done);
        note.setTopic(entity.topic);
        note.setNumber(entity.number);

        return note;
    }

    static NoteEntity convert(Note note) {
        NoteEntity noteEntity = new NoteEntity();

        noteEntity.id = note.getId().toString();
        noteEntity.record = note.getRecord();
        noteEntity.data = note.getData().getTime();
        noteEntity.done = note.isDone();
        noteEntity.topic = note.getTopic();
        noteEntity.number = note.getNumber();

        return noteEntity;
    }
}
