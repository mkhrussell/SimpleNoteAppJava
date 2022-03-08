package com.kamrul.simplenoteapp.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SampleDataProvider {
    private static String sampleText1 = "A sample note.";
    private static String sampleText2 = "A sample note with a\nline feed.";
    private static String sampleText3 = String.format("%s\n%s\n%s\n%s\n%s\n%s",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. ",
            "It has roots in a piece of classical Latin literature from 45 BC, making it over",
            " 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in",
            " Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem ",
            "Ipsum passage, and going through the cites of the word in classical literature, ",
            "discovered the undoubtable source."
            );

    private static Date getDate(Long diff) {
        return new Date(new Date().getTime() + diff);
    }

    public static List<NoteEntity> getNotes() {
        List<NoteEntity> noteEntities = new ArrayList<>();

        noteEntities.add(new NoteEntity(1, getDate(1L), sampleText1));
        noteEntities.add(new NoteEntity(2, getDate(2L), sampleText2));
        noteEntities.add(new NoteEntity(3, getDate(3L), sampleText3));

        return noteEntities;
    }
}
