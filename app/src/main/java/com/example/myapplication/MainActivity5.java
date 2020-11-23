package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity5 extends AppCompatActivity {
    Button button;
    ArrayList<Data>dataArrayList = new ArrayList<>();
    ArrayList<Photo> photoArrayList = new ArrayList<>();
     Data data;
     double n;
     DataEntity dataEntity;
     private MainViewmodel viewmodel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        viewmodel = new ViewModelProvider(this).get(MainViewmodel.class);
        final RecyclerView recyclerView = findViewById(R.id.recyerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setHasFixedSize(true);LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        button = (Button) findViewById(R.id.button2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String name = bundle.getString("name");
        final double kd = bundle.getDouble("kd");
        final double vt = bundle.getDouble("vt");
        final Database database = Room.databaseBuilder(getApplicationContext(),Database.class,"Database").allowMainThreadQueries().build();
        final List<DataEntity> dataEntityArrayList =  database.dataDao().searchData(name,kd,vt);
        if(dataEntityArrayList.size()==0){

        viewmodel.getlist(kd+"", vt+"", new Callback<Response>() {
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
                        dataArrayList.add(data);
                        database.dataDao().insertData(new DataEntity(name, kd, vt, "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg"));
                        DataAdapter dataAdapter2 = new DataAdapter((ArrayList<Data>) dataArrayList, getApplicationContext());
                        recyclerView.setAdapter(dataAdapter2);
            }
        }}

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

            button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewmodel.getlist(n+"" ,n+"", new Callback<Response>() {
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
                                        n++;
                                    }}

                                @Override
                                public void onFailure(Call<Response> call, Throwable t) {

                                }
                            });

                        }
        });
            return ;
        }else {
            Toast.makeText(getApplication(),"load từ databse",Toast.LENGTH_LONG).show();
            databaseAdapter adapter = new databaseAdapter((ArrayList<DataEntity>) dataEntityArrayList,getApplicationContext());
            recyclerView.setAdapter(adapter);


        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        int width = getResources().getDimensionPixelSize(android.R.dimen.thumbnail_height);
//        int height = getResources().getDimensionPixelSize(android.R.dimen.thumbnail_width);
//        get.getWindow().setLayout(width, height);
    }




