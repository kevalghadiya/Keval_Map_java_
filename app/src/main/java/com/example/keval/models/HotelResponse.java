package com.example.keval.models;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
@Dao
public class HotelResponse implements Serializable {
    private int status_code;
    private HotelData data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public HotelData getData() {
        return data;
    }

    public void setData(HotelData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HotelResponse{" +
                "status_code=" + status_code +
                ", data=" + data +
                '}';
    }
}
