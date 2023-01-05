package com.example.keval.models;

import java.io.Serializable;

public class HotelModel implements Serializable {
   private PlaceModel place;

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "HotelModel{" +
                "place=" + place +
                '}';
    }
}
