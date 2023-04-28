package com.example.attex.models;

import java.sql.Timestamp;

public class ModelNote {

    String subject;
    String note;
    String noteTitle;
    String date;
    String noteID;
    Timestamp timestamp;

    public ModelNote(String subject, String note, String noteTitle, String date, String noteID, Timestamp timestamp) {
        this.subject = subject;
        this.note = note;
        this.noteTitle = noteTitle;
        this.date = date;
        this.noteID = noteID;
        this.timestamp = timestamp;
    }

    public ModelNote() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
