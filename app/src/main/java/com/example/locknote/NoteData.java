package com.example.locknote;

public class NoteData {
    private String noteTitle;
    private String noteBody;
    private String noteDeadline;

    public NoteData(String noteTitle, String noteBody, String noteDeadline) {
        this.noteTitle = noteTitle;
        this.noteBody = noteBody;
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
