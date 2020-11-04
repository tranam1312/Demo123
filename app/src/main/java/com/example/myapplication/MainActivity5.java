package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity5 extends AppCompatActivity {
    Button button;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    ArrayList<Data>dataArrayList = new ArrayList<>();
    ArrayList<Photo> photoArrayList = new ArrayList<>();
     Data data;
     int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        button = (Button) findViewById(R.id.button2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String name = bundle.getString("name");
        final double kd = bundle.getDouble("kd");
         final double vt = bundle.getDouble("vt");
        final RecyclerView recyclerView = findViewById(R.id.recyerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutManager);
        final Gson gson = new GsonBuilder().setLenient().create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        SeverApi severApi = retrofit.create(SeverApi.class);
        severApi.list("flickr.photos.search",
                "26797f8e6a03ec7dc3b2c1c0829c307c",kd+"",vt+"","json", "1"

        )
                .enqueue(new Callback<Response>() {
                             @Override
                             public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                 Log.d("id", new Gson().toJson(response.body()));
                                 Response response1 = response.body();
                                 photoArrayList = (ArrayList<Photo>) response1.photos.getPhoto();
                                 Log.d("new ", new Gson().toJson(photoArrayList));
                                 if (photoArrayList.size()==0){
                                     Toast.makeText(getApplication(),"Không còn kết quả ",Toast.LENGTH_SHORT).show();
                                 }
                                 else {
                                     Toast.makeText(getApplication(),"Đã load thành công ",Toast.LENGTH_SHORT).show();
                                     for (int i = 0; i < photoArrayList.size(); i++) {
                                         data = new Data(name, kd + "", vt + "", photoArrayList.get(i).getServer(), photoArrayList.get(i).getId(), photoArrayList.get(i).getSecret());
                                         dataArrayList.add(data);
                                         DataAdapter dataAdapter = new DataAdapter(dataArrayList, getApplicationContext());
                                         recyclerView.setAdapter(dataAdapter);
                                     }
                                 }}


                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final Gson gson = new GsonBuilder().setLenient().create();
                            final Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("https://www.flickr.com/")
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .build();
                            SeverApi severApi = retrofit.create(SeverApi.class);
                            severApi.list("flickr.photos.search",
                                    "26797f8e6a03ec7dc3b2c1c0829c307c",n+"",n+"", "json",
                                    "1"
                            )
                                    .enqueue(new Callback<Response>() {

                                        @RequiresApi(api = Build.VERSION_CODES.N)
                                        @Override
                                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                            Log.d("id", new Gson().toJson(response.body()));
                                            Response response1 = response.body();
                                            photoArrayList = (ArrayList<Photo>) response1.photos.getPhoto();
                                            Log.d("new ", new Gson().toJson(photoArrayList));
                                            if (photoArrayList.size()==0){
                                                Toast.makeText(getApplication(),"Không còn kết quả ",Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(getApplication(),"Đã load thành công ",Toast.LENGTH_SHORT).show();
                                                for (int i = 0; i < photoArrayList.size(); i++) {
                                                    data = new Data(name, kd+"", vt+"", photoArrayList.get(i).getServer(), photoArrayList.get(i).getId(), photoArrayList.get(i).getSecret());
                                                    dataArrayList.add(data);
                                                    DataAdapter dataAdapter = new DataAdapter(dataArrayList, getApplicationContext());
                                                    recyclerView.setAdapter(dataAdapter);

                                                }

                                            }
                                            n++;
                                        }

                                        @Override
                                        public void onFailure(Call<Response> call, Throwable t) {
                                            t.printStackTrace();
                                            Toast.makeText(getApplication(), "lỗi ", Toast.LENGTH_SHORT).show();

                                        }});
                        }
        });}}



