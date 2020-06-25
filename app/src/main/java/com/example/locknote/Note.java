package com.example.locknote;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int noteId;
    private String noteTitle;
    private String noteBody;
    private String noteDeadline;

    public Note(String noteTitle, String noteBody, String noteDeadline) {
        this.noteTitle = noteTitle;
        this.noteBody = noteBody;
        this.noteDeadline = noteDeadline;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public void setNoteDeadline(String noteDeadline) {
        this.noteDeadline = noteDeadline;
    }


    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public String getNoteDeadline() {
        return noteDeadline;
    }
}
