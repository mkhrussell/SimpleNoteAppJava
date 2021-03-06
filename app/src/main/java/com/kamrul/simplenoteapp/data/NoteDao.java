package com.kamrul.simplenoteapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity note);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNotes(List<NoteEntity> notes);

    @Query("SELECT * FROM notes ORDER BY date ASC")
    LiveData<List<NoteEntity>> getAll();

    @Query("SELECT * FROM notes WHERE id = :id")
    NoteEntity getNoteById(int id);

    @Query("SELECT COUNT(*) FROM notes")
    int getCount();

    @Delete
    int deleteNotes(List<NoteEntity> selectedNotes);

    @Query("DELETE FROM notes")
    int deleteAllNotes();

    @Delete
    void deleteNote(NoteEntity value);
}
