package com.example.shakt.baked;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Shakti on 23-03-2018.
 */


// This class is a Recycles view adapter class that maps the data to the cards in recycler view

public class mapRecycleViewAdapter extends RecyclerView.Adapter<mapRecycleViewAdapter.ViewHolder> {

    private static final String TAG = "com.example.shakt.baked.mapRecycleViewAdapter";

    private ArrayList<String> mAddress;
    private ArrayList<String> mImageUrl;
    private Context context;

    mapRecycleViewAdapter(Context context, ArrayList<String> mAddress, ArrayList<String> mImageUrl) {
        this.mAddress = mAddress;
        this.mImageUrl = mImageUrl;
        this.context = context;
    }

    // View holder to
    @Override
    public mapRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_mapcards, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imageView.setImageResource(R.drawable.nslc_logo);


        holder.address.setText(mAddress.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddress.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImage);
            address = itemView.findViewById(R.id.mapcard_text);
        }
    }
}
