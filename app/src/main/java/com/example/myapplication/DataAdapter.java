package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    ArrayList<Data>dataArrayList;

    @Override
    public int getItemViewType(int position) {
            return dataArrayList.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM; // So sánh nếu item được get tại vị trí này là null thì view đó là loading view , ngược lại là item
    }

    Context context;

    public DataAdapter(ArrayList<Data>dataArrayList, Context context) {
        this.dataArrayList = dataArrayList;
        this.context = context;
    }
    public  void AddLisAdapter(ArrayList<Data>arrayList ){
        dataArrayList.addAll(arrayList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item,parent,false);
        return new ViewHodlerItem(itemView);
        }else if (viewType == VIEW_TYPE_LOADING) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View item = layoutInflater.inflate(R.layout.item_loadmore, parent, false);
            return  new LoadingViewHolder(item);

        }
        return  null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHodlerItem){
            Data data = dataArrayList.get(position);
            ViewHodlerItem viewHodlerItem = (ViewHodlerItem) holder;
            viewHodlerItem.nam.setText("Name: " + dataArrayList.get(position).getName());
            viewHodlerItem.kd.setText("latitude: " + dataArrayList.get(position).getKinhDo());
            viewHodlerItem.vt.setText("Longidutie: " + dataArrayList.get(position).getViDo());
            Glide.with(context).load(dataArrayList.get(position).getUrl()).into(viewHodlerItem.imageView);
        }else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder =  (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);

        }
    }

    @Override
    public int getItemCount() {
        return (null != dataArrayList ? dataArrayList.size() : 0);
    }

    public class ViewHodlerItem extends RecyclerView.ViewHolder{
        TextView nam, kd,vt;

        ImageView imageView;

        public ViewHodlerItem(@NonNull View itemView) {
            super(itemView);
            nam =(TextView) itemView.findViewById(R.id.name);
            kd = (TextView) itemView.findViewById(R.id.kinhDo);
            vt = (TextView) itemView.findViewById(R.id.viDo);
            imageView=(ImageView) itemView.findViewById(R.id.imgView);
            nam.setSelected(true);
        }


    }
  public class LoadingViewHolder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
    }
  }
}

