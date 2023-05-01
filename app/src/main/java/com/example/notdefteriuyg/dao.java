package com.example.notdefteriuyg;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
