package com.example.keval.HotelDBMSModule;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.keval.models.HotelModel;
import com.example.keval.models.PlaceModel;

import java.util.ArrayList;
@Dao
public interface HotelDao {
   // @Insert
   // void insertAllData(ArrayList<PlaceModel> placeModels);

    @Insert
    void insertAllData(PlaceModel placeModel);

  /*  @Insert
    void insertAllData(PlaceModel placeModel);*/
}
