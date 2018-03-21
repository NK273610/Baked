package com.example.shakt.baked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class OrderHistortActivity extends AppCompatActivity {
    Toolbar toolbar ;
    UserInfoClass obj ;
    public static final String FIREBASE_CHILD_USERS = "Users";
    public String User_id = "0";

    public  List<String> order_history;

    public  List<String> pictures;
    public ListViewCompat order_list_view;
    public CustomListadapter order_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_histort);
        toolbar = (Toolbar) findViewById(R.id.orderHistory_toolbar);
        toolbar.setTitle(getString(R.string.order_history));

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
         DatabaseReference myRef = mydatabase.getReference(FIREBASE_CHILD_USERS);
        //Enter the user id of the user who we want the reviews of
        myRef=myRef.child(User_id);
        final DatabaseReference myRef2 = mydatabase.getReference("Products");
        ValueEventListener valueEventListener = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
//                    obj = userDataSnapshot.getValue(UserInfoClass.class);
                     obj = dataSnapshot.getValue(UserInfoClass.class);
                }
                order_history = obj.getOrder_History();

                // TODO add an array of product pics in the firebase



                order_list_adapter = new CustomListadapter();
                order_list_view =  findViewById(R.id.order_history_list);
                order_list_view.setAdapter(order_list_adapter);

//                for(int i =0 ;i<reviews.size();i+=2) product_name.add(reviews.get(i));
                order_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            return (int)(order_history.size()/2);
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
            convertView = getLayoutInflater().inflate(R.layout.customlist_reviews,null);
            ImageView img = convertView.findViewById(R.id.product_pic);
            TextView product_name = convertView.findViewById(R.id.product_name);
            TextView review = convertView.findViewById(R.id.review);
            img.setImageResource(imagearray[position]);
            product_name.setText(order_history.get(name_position));
            review.setText(order_history.get(description_position));
            return convertView;
        }
    }
}
