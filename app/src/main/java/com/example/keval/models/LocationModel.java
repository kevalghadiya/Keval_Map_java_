package com.example.keval.models;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Collection;

public class LocationModel implements Serializable {
    Double lat;
    Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
