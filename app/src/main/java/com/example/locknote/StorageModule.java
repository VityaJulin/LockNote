package com.example.locknote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule extends AppCompatActivity {
    NotesDataBase notesDataBase;

    @Provides
    NotesDataBase getStorage() {
        notesDataBase = Room.databaseBuilder(getApplicationContext(), NotesDataBase.class, "db_notes").build();
        return notesDataBase;
    }


}
