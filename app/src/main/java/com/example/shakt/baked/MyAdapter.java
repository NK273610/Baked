package com.example.shakt.baked;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nikhildhirmalani on 25/03/18.
 */

class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;//context c
    List<String> url;//list of string url

    public MyAdapter(Context c, List<String> url) {
        this.c = c; //myadapter constructor
        this.url = url;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) { //holder class which inflates strain_images
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.strain_images,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {


        PicassoClient.downloadImage(c,url.get(position),holder.img);//picasso used to get image from url and place in imageview
    } //picasoClient class method called

    @Override
    public int getItemCount() {

        return url.size();
    }
}

class PicassoClient { //picasso client class

    public static void downloadImage(Context c,String url,ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.get().load(url).placeholder(R.drawable.placeholder).into(img);// picasso class
        }else {

        }
    }
}
class MyHolder extends RecyclerView.ViewHolder {//holder class


    ImageView img; //imageview

    public MyHolder(View itemView) {
        super(itemView);


        img= (ImageView) itemView.findViewById(R.id.imageView);//the imageview id taken

    }
}