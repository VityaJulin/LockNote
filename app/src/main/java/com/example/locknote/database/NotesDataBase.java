package com.example.locknote.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.locknote.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDataBase extends RoomDatabase {
    public abstract NoteDao getNoteDao();
}
