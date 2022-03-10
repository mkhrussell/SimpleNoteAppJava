package com.kamrul.simplenoteapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.kamrul.simplenoteapp.Constants;

@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    abstract NoteDao noteDao();
    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME).build();
            }
        }
        return INSTANCE;
    }
}
