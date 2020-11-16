package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public   class viewmodel extends ViewModel implements Callback<Response> {


    Database database = Room.databaseBuilder(getApplicationContext(),Database.class,"Database").allowMainThreadQueries().build();
    String name ;
    double kd,vt;

    @SuppressLint("ResourceType")
    public viewmodel() {
        recyclerView = recyclerView.findViewById(R.layout.activity_main5);
    }

    public void name(String name){
       this.name = name;
    }
    public void Kd(double kd){
        this.kd =kd;
    }
    public void Vt(double vt){
        this.vt =vt;
    }

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplication());
    @SuppressLint("ResourceType")
    RecyclerView recyclerView;


    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        Log.d("id", new Gson().toJson(response.body()));
        Response response1 = response.body();
        ArrayList<Photo> photoArrayList = response1.photos.getPhoto();
        Log.d("new ", new Gson().toJson(photoArrayList));
        if (photoArrayList.size() == 0) {
            Toast.makeText(getApplication(), " ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplication(), "", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < photoArrayList.size(); i++) {
                Data data = new Data(name, kd+"", vt + "", "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg");
                List<Data> dataArrayList = null;
                dataArrayList.add(data);
                database.dataDao().insertData(new DataEntity(name, kd, vt, "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg"));
                DataAdapter dataAdapter = new DataAdapter((ArrayList<Data>) dataArrayList, getApplicationContext());
                recyclerView.setAdapter(dataAdapter);

            }
        }

    }
    public Context getApplication() {
        return getApplication();
    }

    public Context getApplicationContext() {
        return getApplicationContext();
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {

    }

}
