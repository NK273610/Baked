package com.example.shakt.baked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tgup1 on 04-04-2018.
 */

public class Review_CustomAdapter extends BaseAdapter{
    private Context context ;
    int[] imagearray = {R.drawable.ganjaoldy,R.drawable.ganjaoldy,R.drawable.ganjaoldy};
    Product_Class data;
  Review_CustomAdapter(Context c ,Product_Class data){
      this.context = c;
      this.data = data;
  }

    @Override
    public int getCount() {
        return (int)(data.getReviews().size());
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        convertView =  LayoutInflater.from(context).inflate(R.layout.customlist_reviews_for_product,null);
        ImageView img = convertView.findViewById(R.id.product_pic);
        TextView product_name = convertView.findViewById(R.id.product_name);
        TextView review = convertView.findViewById(R.id.review);
        RatingBar ratingBar = convertView.findViewById(R.id.Custom_review_list_ratingbar);
        img.setImageResource(imagearray[position]);
        product_name.setText("Nam toh add karo db mein");
        review.setText(data.getReviews().get(position));
        ratingBar.setRating(data.getRating().get(position));
        return convertView;
    }
}
