package com.example.myapplication;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public   class MainViewmodel extends ViewModel{


//    Database database = Room.databaseBuilder(getApplicationContext(),Database.class,"Database").allowMainThreadQueries().build();
//    String name ;name
//    double kd,vt;name



    public void getlist(String kd,String vt,Callback<Response> lisener){
        final Gson gson = new GsonBuilder().setLenient().create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        SeverApi severApi = retrofit.create(SeverApi.class);
         severApi.list("flickr.photos.search",
                "e76f0f04c064b82975db423af9ff41f5",kd+"",vt+"","json", "1").enqueue(lisener);
    }

}