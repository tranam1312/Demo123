package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHodler> {

    ArrayList<Data>dataArrayList;
    Context context;

    public DataAdapter(ArrayList<Data>dataArrayList, Context context) {
        this.dataArrayList = dataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
       holder.nam.setText("Name: "+dataArrayList.get(position).getName());
       holder.kd.setText( "Latidutie: "+ dataArrayList.get(position).getKinhDo());
       holder.vt.setText("Longidutie: "+dataArrayList.get(position).getViDo());
       Glide.with(context).load("https://live.staticflickr.com/" + dataArrayList.get(position).getId()+ "/" + dataArrayList.get(position).getSecret()+ "_" + dataArrayList.get(position).getServer() + ".jpg").into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
    public class ViewHodler extends RecyclerView.ViewHolder{
        TextView nam, kd,vt;

        ImageView imageView;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            nam =(TextView) itemView.findViewById(R.id.name);
            kd = (TextView) itemView.findViewById(R.id.kinhDo);
            vt = (TextView) itemView.findViewById(R.id.viDo);
            imageView=(ImageView) itemView.findViewById(R.id.imgView);

        }


    }
}
