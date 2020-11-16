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
import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

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
     DataEntity dataEntity;
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
        final Database database = Room.databaseBuilder(getApplicationContext(),Database.class,"Database").allowMainThreadQueries().build();


        List<DataEntity> dataEntityArrayList =  database.dataDao().searchData(name,kd,vt);
        if(dataEntityArrayList.size()==0){

        final Gson gson = new GsonBuilder().setLenient().create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        SeverApi severApi = retrofit.create(SeverApi.class);
        severApi.list("flickr.photos.search",
                "b7de916df5718fb321f56401538f1f87",kd+"",vt+"","json", "1"

        )
                .enqueue()



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
                                    "b7de916df5718fb321f56401538f1f87",n+"",n+"", "json",
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
                                                Toast.makeText(getApplication(),"load từ api",Toast.LENGTH_LONG).show();
                                                Toast.makeText(getApplication(),"Đã load thành công ",Toast.LENGTH_SHORT).show();
                                                for (int i = 0; i < photoArrayList.size(); i++) {
                                                    data = new Data(name, kd+"", vt+"", "https://live.staticflickr.com/"+photoArrayList.get(i).getServer().toString()+"/"+photoArrayList.get(i).getId().toString()+"_"+ photoArrayList.get(i).getSecret().toString()+".jpg");
                                                    dataArrayList.add(data);
                                                    database.dataDao().insertData(new DataEntity(name,kd,vt,"https://live.staticflickr.com/"+photoArrayList.get(i).getServer().toString()+"/"+photoArrayList.get(i).getId().toString()+"_"+ photoArrayList.get(i).getSecret().toString()+".jpg"));

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
        });
        }else {
            Toast.makeText(getApplication(),"load từ databse",Toast.LENGTH_LONG).show();
            databaseAdapter adapter = new databaseAdapter((ArrayList<DataEntity>) dataEntityArrayList,getApplicationContext());
            recyclerView.setAdapter(adapter);


        }
    }
}



