package com.example.locknote;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertAll(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note")
    List<Note> getAllNote();

}
