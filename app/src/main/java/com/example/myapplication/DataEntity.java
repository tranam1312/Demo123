package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data")
public class DataEntity {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name= "latitude")
    private Double latitude;
    @ColumnInfo(name = "longitude")
    private Double longitude;
    @ColumnInfo(name = "url")
    private String url;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataEntity(String name, Double latitude, Double longitude, String url) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
    }

}




