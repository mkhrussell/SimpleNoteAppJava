package com.kamrul.simplenoteapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kamrul.simplenoteapp.data.AppDatabase;
import com.kamrul.simplenoteapp.data.NoteEntity;

public class EditorViewModel extends AndroidViewModel {
    private final AppDatabase database;
    private final MutableLiveData<NoteEntity> currentNote = new MutableLiveData<>();

    public EditorViewModel(@NonNull Application app) {
        super(app);
        this.database = AppDatabase.getInstance(app);
    }

    public MutableLiveData<NoteEntity> getCurrentNote() {
        return currentNote;
    }

    public void getNoteById(int noteId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            NoteEntity note = null;
            if(noteId != Constants.NEW_NOTE_ID) {
                note = database.noteDao().getNoteById(noteId);
            } else {
                note = new NoteEntity();
            }
            currentNote.postValue(note);
        });
    }
}