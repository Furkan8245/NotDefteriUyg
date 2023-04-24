package com.example.notdefteriuyg;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface dao {
    @Insert
    public void noteAdd(Note note);

    @Query("SELECT * FROM notes")
    public List<Note> getNote();

    @Delete
    public void noteDelete(Note note);

    @Update
    public void noteUpdate(Note note);



}
