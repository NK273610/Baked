package com.example.shakt.baked;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by hardi on 4/1/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder>{


    Context c;
    List<String> mProduct;

    public ProductAdapter(Context c, List<String> mProduct) {
        this.c = c;
        this.mProduct = mProduct;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
        ProductHolder holder=new ProductHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {


        //PicassoClient.downloadImage(c,url.get(position),holder.img);
        TextView text=holder.text;
        text.setText(mProduct.get(position));

    }

    @Override
    public int getItemCount() {

        return mProduct.size();
    }
}

class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView text;
    View view;

    public ProductHolder(View itemView) {
        super(itemView);
        view = itemView;


        text= (TextView) itemView.findViewById(R.id.product_text);
        view.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String mStrain = text.getText().toString();
        Intent intent = new Intent(itemView.getContext(),RecommendationActivity.class);
        intent.putExtra("Strain",mStrain);
        itemView.getContext().startActivity(intent);
    }
}