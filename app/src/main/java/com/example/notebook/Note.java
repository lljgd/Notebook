package com.example.notebook;

import java.util.Date;
import java.util.UUID;

public class Note {

    private UUID id;
    private String record;
    private Date data;
    private boolean done;
    private String topic;
    private int number;

    public Note() {
        this.id = UUID.randomUUID();
        this.data = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getData() {
        return data;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getNumber() {
        return number;
    }

    private int setNumber(int number) {
        return number;
    }
}
