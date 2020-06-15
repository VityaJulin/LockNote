package com.example.locknote;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDataBase extends RoomDatabase {
    public abstract NoteDao getNoteDao();
}
