package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    EditText name, kD,vt ;
    Button btn;
    ArrayList<Data>dataArrayList = new ArrayList<>();
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        name = (EditText) findViewById(R.id.etxtName);
        kD = (EditText) findViewById((R.id.etxtLatidue));
        vt = (EditText) findViewById(R.id.etxtLongidue);
        btn = (Button) findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                final Gson gson = new GsonBuilder().setLenient().create();
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.flickr.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                SeverApi severApi = retrofit.create(SeverApi.class);
                severApi.list("flickr.photos.search",
                        "43e8b30cee47ec2ae11e515dd9035fc6", kD.getText().toString(),vt.getText().toString(), "json",
                        "1"
                )
                        .enqueue(new Callback<Response>() {

                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                Log.d("id", new Gson().toJson(response.body()));
                                Response response1 = response.body();
                                ArrayList<Photo> photoArrayList = (ArrayList<Photo>) response1.photos.getPhoto();

                                Log.e("n", String.valueOf(photoArrayList.size()));
                                Log.d("new ", new Gson().toJson(photoArrayList));
                                for (int i =0;i<photoArrayList.size();i++)
                                     {
                                         data = new Data(name.toString(), kD+ "", vt + "", "https://live.staticflickr.com/"+photoArrayList.get(i).getServer().toString()+"/"+photoArrayList.get(i).getId().toString()+"_"+ photoArrayList.get(i).getSecret().toString()+"jpg");

                                         dataArrayList.add(data);
                                     }
                                Intent intent = new Intent(getApplication(), MainActivity5.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("name",name.getText().toString());
                                bundle.putString("kd",kD.getText().toString());
                                bundle.putString("vt",vt.getText().toString());
                                bundle.putSerializable("nam",  dataArrayList);
                                intent.putExtras(bundle);
                                startActivity(intent);


                            }
                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplication(), "lỗi ", Toast.LENGTH_SHORT).show();


                            }



                        });

            }});}}






















