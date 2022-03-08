package com.kamrul.simplenoteapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.data.SampleDataProvider;

import java.util.List;

public class MainViewModel extends ViewModel {
    MutableLiveData<List<NoteEntity>> notesList = new MutableLiveData<>();

    public MainViewModel() {
        this.notesList.setValue(SampleDataProvider.getNotes());
    }

}