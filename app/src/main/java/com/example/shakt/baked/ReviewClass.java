package com.example.shakt.baked;

import android.app.Fragment;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ReviewClass extends android.support.v4.app.Fragment {

    TextView text;
    Product_Class obj;
    ListViewCompat listView;
    Review_CustomAdapter review_customAdapter;
    private String FIREBASE_CHILD_PRODUCTS="Products";
    String value ;
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState){
        Bundle bundle = this.getArguments();
        if (bundle.containsKey("strainName")){
            value= bundle.getString("strainName");
        }
        return inflater.inflate(R.layout.review, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FIREBASE_CHILD_PRODUCTS);
        myRef = myRef.child(value);
        obj=new Product_Class();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    obj = dataSnapshot.getValue(Product_Class.class);
                    Log.e("TAG",obj.getReviews().get(1));

                }
                listView = getView().findViewById(R.id.Custom_review_list);
                review_customAdapter = new Review_CustomAdapter(getContext(),obj);
                listView.setAdapter(review_customAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });




    }
}
