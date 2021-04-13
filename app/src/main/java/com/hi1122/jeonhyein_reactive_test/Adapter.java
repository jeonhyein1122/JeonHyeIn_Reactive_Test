package com.hi1122.jeonhyein_reactive_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public Adapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item,parent,false);
        VH holder=new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        Item item=items.get(position);
        vh.title.setText(item.title);
        vh.rating.setText(item.rating);
        Glide.with(context).load(item.image).into(vh.image);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView title;
        TextView rating;

        public VH(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            rating=itemView.findViewById(R.id.rating);
        }
    }
}
