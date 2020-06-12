package com.example.locknote;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String noteTitle;
    private String noteBody;
    private String noteDeadline;

    public Note(String noteTitle, String noteBody, String noteDeadline) {
        this.noteTitle = noteTitle;
        this.noteBody = noteBody;
        this.noteDeadline = noteDeadline;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
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
