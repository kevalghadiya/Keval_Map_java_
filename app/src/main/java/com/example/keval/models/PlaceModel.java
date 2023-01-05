package com.example.keval.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "hotel_table")
public class PlaceModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String name_suffix;
    private int star_rating;
    private String thumbnail_url;
    private String marker;
//    private LocationModel location;

    public PlaceModel(String name, String name_suffix, int star_rating, String thumbnail_url, String marker) {
        this.name = name;
        this.name_suffix = name_suffix;
        this.star_rating = star_rating;
        this.thumbnail_url = thumbnail_url;
        this.marker = marker;
       // this.location = location;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

  /*  public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_suffix() {
        return name_suffix;
    }

    public void setName_suffix(String name_suffix) {
        this.name_suffix = name_suffix;
    }

    public int getStar_rating() {
        return star_rating;
    }

    public void setStar_rating(int star_rating) {
        this.star_rating = star_rating;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    @Override
    public String toString() {
        return "PlaceModel{" +
                "name='" + name + '\'' +
                ", name_suffix='" + name_suffix + '\'' +
                ", star_rating=" + star_rating +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                '}';
    }
}
