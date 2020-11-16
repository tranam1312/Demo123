package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Photos {
    @SerializedName("page")
      int page ;
    @SerializedName("pages")
      int  pages;
    @SerializedName("total")
    int total;
    @SerializedName("photo")
    ArrayList<Photo> photo;
    public Photos(int page, int pages, int total, ArrayList<Photo> photo) {
        this.page = page;
        this.pages = pages;
        this.total = total;
        this.photo = photo;
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getTotal() {
        return total;
    }


    public ArrayList<Photo> getPhoto() {
        return photo;
    }
}
