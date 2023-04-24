package com.example.notdefteriuyg;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "note")
    public String note;

    @ColumnInfo(name = "clock")
    public String noteclock;

    @ColumnInfo(name="date")
    public String notedate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteclock() {
        return noteclock;
    }

    public void setNoteclock(String noteclock) {
        this.noteclock = noteclock;
    }

    public String getNotedate() {
        return notedate;
    }

    public void setNotedate(String notedate) {
        this.notedate = notedate;
    }
}
