package com.kamrul.simplenoteapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kamrul.simplenoteapp.data.AppDatabase;
import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.data.SampleDataProvider;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final AppDatabase database;
    private final LiveData<List<NoteEntity>> notesList;

    public MainViewModel(Application app) {
        super(app);
        this.database = AppDatabase.getInstance(app);
        this.notesList = database.noteDao().getAll();
    }

    public LiveData<List<NoteEntity>> getNotesList() {
        return notesList;
    }

    public void addSampleData() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.noteDao().insertNotes(SampleDataProvider.getNotes());
            }
        });
    }
}