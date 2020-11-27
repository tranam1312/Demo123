package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity5 extends AppCompatActivity {
    Button button;
    ArrayList<Data>dataArrayList = new ArrayList<>();
    ArrayList<Photo> photoArrayList = new ArrayList<>();
     Data data;
     double n;
     DataEntity dataEntity;
     private MainViewmodel viewmodel;
    RecyclerView recyclerView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        viewmodel = new ViewModelProvider(this).get(MainViewmodel.class);
        recyclerView = findViewById(R.id.recyerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        button = (Button) findViewById(R.id.button2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String name = bundle.getString("name");
        final double kd = bundle.getDouble("kd");
        final double vt = bundle.getDouble("vt");
        final Database database = Room.databaseBuilder(getApplicationContext(), Database.class, "Database").allowMainThreadQueries().build();
        final List<DataEntity> dataEntityArrayList = database.dataDao().searchData(name, kd, vt);
        if (dataEntityArrayList.size() == 0) {
            viewmodel.getlist(kd + "", vt + "", new Callback<Response1>() {
                @Override
                public void onResponse(Call<Response1> call, Response<Response1> response) {
                    Log.d("id", new Gson().toJson(response.body()));
                    Response1 response1 = response.body();
                    ArrayList<Photo> photoArrayList = response1.photos.getPhoto();
                    Log.d("new ", new Gson().toJson(photoArrayList));
                    if (photoArrayList.size() == 0) {
                        Toast.makeText(getApplication(), " ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < photoArrayList.size(); i++) {
                            Data data = new Data(name, kd + "", vt + "", "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg");
                            dataArrayList.add(data);
                            database.dataDao().insertData(new DataEntity(name, kd, vt, "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg"));
                        }
                        DataAdapter dataAdapter2 = new DataAdapter((ArrayList<Data>) dataArrayList, getApplicationContext());
                        recyclerView.setAdapter(dataAdapter2);
                    }
                }
                @Override
                public void onFailure(Call<Response1> call, Throwable t) {
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewmodel.getlist(n + "", n + "", new Callback<Response1>() {
                        @Override
                        public void onResponse(Call<Response1> call, Response<Response1> response) {
                            Log.d("id", new Gson().toJson(response.body()));
                            Response1 response1 = response.body();
                            ArrayList<Photo> photoArrayList = response1.photos.getPhoto();
                            Log.d("new ", new Gson().toJson(photoArrayList));
                            if (photoArrayList.size() == 0) {
                                Toast.makeText(getApplication(), " ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplication(), "", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < photoArrayList.size(); i++) {
                                    Data data = new Data(name, kd + "", vt + "", "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg");
                                    List<Data> dataArrayList = null;
                                    dataArrayList.add(data);
                                    database.dataDao().insertData(new DataEntity(name, kd, vt, "https://live.staticflickr.com/" + photoArrayList.get(i).getServer().toString() + "/" + photoArrayList.get(i).getId().toString() + "_" + photoArrayList.get(i).getSecret().toString() + ".jpg"));

                                }
                                DataAdapter dataAdapter2 = new DataAdapter((ArrayList<Data>) dataArrayList, getApplicationContext());
                                recyclerView.setAdapter(dataAdapter2);
                                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                        super.onScrollStateChanged(recyclerView, newState);
                                        recyclerView.setHasTransientState(true);

                                    }
                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        super.onScrolled(recyclerView, dx, dy);
                                    }
                                });
                                n++;
                            }
                        }
                        @Override
                        public void onFailure(Call<Response1> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"lỗi ",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DataAdapter dataAdapter2 = new DataAdapter((ArrayList<Data>) dataArrayList, getApplicationContext());
                            recyclerView.setAdapter(dataAdapter2);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 2500);
                }
            });
        } else {
            Toast.makeText(getApplication(), "load từ databse", Toast.LENGTH_LONG).show();
            databaseAdapter adapter = new databaseAdapter((ArrayList<DataEntity>) dataEntityArrayList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        databaseAdapter adapter = new databaseAdapter((ArrayList<DataEntity>) dataEntityArrayList, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }

        });
    }
}}






