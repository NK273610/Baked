package com.example.shakt.baked;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shakt.baked.R;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class Reviews extends AppCompatActivity {
    Toolbar toolbar;
    UserInfoClass obj ;
    public static final String FIREBASE_CHILD_USERS = "Users";
    public String User_id = "0";
    //variables for accesing data from
    //firebase
    public List<String> reviews ;
    public  List<String> order_history;
    public  List<String> Ratings;
    public  List<String> pictures;
    public ListViewCompat review_list_view;
    public CustomListadapter review_list_adapter ;
    //product info page
    public List<String> product_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        toolbar = (Toolbar) findViewById(R.id.review_toolbar);
        toolbar.setTitle(getString(R.string.reviews));
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



         FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
         DatabaseReference myRef = mydatabase.getReference(FIREBASE_CHILD_USERS);
        //Enter the user id of the user who we want the reviews of
        myRef = myRef.child(User_id);
        ValueEventListener valueEventListener = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                      obj = dataSnapshot.getValue(UserInfoClass.class);
//                    obj = userDataSnapshot.getValue(UserInfoClass.class);
                }
                reviews = obj.getReviews();

                // TODO add an array of product pics in the firebase



                review_list_adapter = new CustomListadapter();
                review_list_view =  findViewById(R.id.review_list);
                review_list_view.setAdapter(review_list_adapter);

//                for(int i =0 ;i<reviews.size();i+=2) product_name.add(reviews.get(i));
                review_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),RecommendationActivity.class);
                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });




    }

    class CustomListadapter extends BaseAdapter {
        //TODO remove this temp array when all pictures are saved in DB
        int[] imagearray = {R.drawable.ganjaoldy,R.drawable.ganjaoldy,R.drawable.ganjaoldy};

        @Override
        public int getCount() {
            return (int)(reviews.size()/2);
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
            int name_position;
            int description_position;
           name_position = 2*position;
           description_position = name_position+1;
//inflate
            convertView = getLayoutInflater().inflate(R.layout.customlist_reviews,null);
            ImageView img = convertView.findViewById(R.id.product_pic);
            TextView product_name = convertView.findViewById(R.id.product_name);
            TextView review = convertView.findViewById(R.id.review);
            img.setImageResource(imagearray[position]);
            product_name.setText(reviews.get(name_position));
            review.setText(reviews.get(description_position));
            return convertView;
        }
    }
}



