package com.example.keval.RoomDataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.keval.models.HotelResponse;

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
