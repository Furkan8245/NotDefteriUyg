package com.example.notdefteriuyg;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {Note.class}, version = 1)
public abstract class database extends RoomDatabase {
    public abstract dao dao();


}
