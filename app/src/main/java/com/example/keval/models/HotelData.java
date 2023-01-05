package com.example.keval.models;

import java.io.Serializable;
import java.util.List;

public class HotelData implements Serializable {
    private List<HotelModel> hotels;

    public List<HotelModel> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelModel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "HotelData{" +
                "hotels=" + hotels +
                '}';
    }

}
