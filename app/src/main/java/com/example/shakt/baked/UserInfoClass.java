package com.example.shakt.baked;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by tgup1 on 20-03-2018.
 */


public class UserInfoClass {
    private List<String> Rating;
    private List<String> Order_History;
    private List<String> Reviews;
    private List<String> Product_Pic;

    public List<String> getProduct_Pic() {
        return Product_Pic;
    }

    public void setProduct_Pic(List<String> Product_Pic) {
        this.Product_Pic = Product_Pic;
    }


    public List<String> getRating() {
        return Rating;
    }

    public void setRating(List<String> Rating) {
        this.Rating = Rating;
    }

    public List<String> getOrder_History() {
        return Order_History;
    }

    public void setOrder_History(List<String> Order_History) {
        this.Order_History = Order_History;
    }

    public List<String> getReviews() {
        return Reviews;
    }

    public void setReviews(List<String> Reviews) {
        this.Reviews = Reviews;
    }
}
