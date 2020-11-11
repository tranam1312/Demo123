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


public class databaseAdapter  extends RecyclerView.Adapter<databaseAdapter.ViewHodler> {

    ArrayList<DataEntity> dataEntityArrayList;
    Context context;
    public databaseAdapter( ArrayList<DataEntity> dataEntityArrayList, Context context) {
        this.dataEntityArrayList=dataEntityArrayList;
        this.context = context;
    }
    @NonNull
    @Override


    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View  view = layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull databaseAdapter.ViewHodler holder, int position) {
        holder.nam.setText("Name: "+dataEntityArrayList.get(position).getName());
        holder.kd.setText( "latitude: "+ dataEntityArrayList.get(position).getLatitude());
        holder.vt.setText("Longidutie: "+dataEntityArrayList.get(position).getLongitude());
        Glide.with(context).load(dataEntityArrayList.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataEntityArrayList.size();
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
