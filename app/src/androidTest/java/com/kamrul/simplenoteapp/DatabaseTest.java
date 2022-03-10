package com.kamrul.simplenoteapp;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.kamrul.simplenoteapp.data.AppDatabase;
import com.kamrul.simplenoteapp.data.NoteDao;
import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.data.SampleDataProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private NoteDao noteDao;
    private AppDatabase database;

    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        noteDao = database.noteDao();
    }

    @Test
    public void createNotes() {
        noteDao.insertNotes(SampleDataProvider.getNotes());
        int count = noteDao.getCount();
        assertEquals(count, SampleDataProvider.getNotes().size());
    }

    @Test
    public void insertNote() {
        NoteEntity note = new NoteEntity();
        note.setText("This is a test note.");
        noteDao.insertNote(note);

        NoteEntity noteFromDb = noteDao.getNoteById(1);
        assertEquals(1, noteFromDb==null? 0 : noteFromDb.getId());
    }

    @After
    public void closeDb() {
        database.close();
        noteDao = null;
    }
}
