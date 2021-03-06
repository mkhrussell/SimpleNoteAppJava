package com.kamrul.simplenoteapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kamrul.simplenoteapp.data.AppDatabase;
import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.data.SampleDataProvider;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final AppDatabase database;
    private final LiveData<List<NoteEntity>> notesList;

    public MainViewModel(@NonNull Application app) {
        super(app);
        this.database = AppDatabase.getInstance(app);
        this.notesList = database.noteDao().getAll();
    }

    public LiveData<List<NoteEntity>> getNotesList() {
        return notesList;
    }

    public void addSampleData() {
        AppDatabase.databaseWriteExecutor.execute(() -> database.noteDao().insertNotes(SampleDataProvider.getNotes()));
    }

    public void deleteNotes(List<NoteEntity> selectedNotes) {
        AppDatabase.databaseWriteExecutor.execute(() -> database.noteDao().deleteNotes(selectedNotes));
    }

    public void deleteAllNotes() {
        AppDatabase.databaseWriteExecutor.execute(() -> database.noteDao().deleteAllNotes());
    }
}