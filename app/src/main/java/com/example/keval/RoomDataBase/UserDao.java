package com.example.keval.RoomDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.keval.models.HotelResponse;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertAllData(User users);
//    void insertAllData(HotelResponse hotelResponse);

    @Query("SELECT * FROM User")
    List<User> getAll();
}
