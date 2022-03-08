package com.kamrul.simplenoteapp.data;

import com.kamrul.simplenoteapp.Constants;

import java.util.Date;

public class NoteEntity {
    int id;
    Date date;
    String text;

    public NoteEntity() {
        this(Constants.NEW_NOTE_ID, new Date(), "");
    }

    public NoteEntity(Date date, String text) {
        this(Constants.NEW_NOTE_ID, date, text);
    }

    public NoteEntity(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}