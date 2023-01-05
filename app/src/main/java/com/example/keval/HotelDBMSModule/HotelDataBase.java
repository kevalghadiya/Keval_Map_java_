package com.example.keval.HotelDBMSModule;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.keval.models.HotelResponse;
import com.example.keval.models.PlaceModel;

@Database(entities = {PlaceModel.class},version = 1)
public abstract class HotelDataBase extends RoomDatabase {
    public abstract HotelDao hotelDao();

}
