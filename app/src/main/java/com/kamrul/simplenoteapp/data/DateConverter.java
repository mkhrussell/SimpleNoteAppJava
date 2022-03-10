package com.kamrul.simplenoteapp.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return new Date(value);
    }

    @TypeConverter
    public Long fromDateToTimestamp(Date date) {
        return date.getTime();
    }
}
