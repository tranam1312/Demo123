package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Response1 {

    @SerializedName("photos")
      Photos photos;

    @SerializedName("stat")
    private String stat;

    public Response1(Photos photos) {
        this.photos = photos;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
