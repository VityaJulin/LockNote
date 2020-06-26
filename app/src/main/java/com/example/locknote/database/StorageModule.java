package com.example.locknote.database;

import android.content.Context;

import androidx.room.Room;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
    private final Context mContext;
    private final String DATA_BASE_NAME = "note_database";

    public StorageModule(Context context) {
        mContext = context;
    }

    @Provides
    public NotesDataBase getStorage() {
        return Room.databaseBuilder(mContext, NotesDataBase.class, DATA_BASE_NAME).build();
    }
}
