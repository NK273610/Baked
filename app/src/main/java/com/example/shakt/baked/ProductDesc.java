package com.example.shakt.baked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDesc extends AppCompatActivity {

    DatabaseReference typeReference;
    Product_Class productHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_desc);
        Intent intent = getIntent();
        String typeProd = intent.getStringExtra("type");
        final String[] prodName = new String[1];

        Toast.makeText(getApplicationContext(), typeProd, Toast.LENGTH_SHORT).show();
        productHandler = new Product_Class();
        Log.e("New TAg",typeProd);
        typeReference = FirebaseDatabase.getInstance().getReference("Type").child(typeProd);

        typeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> productHandlerList = (ArrayList<String>) dataSnapshot.getValue();
                Log.e("Here", "" + productHandlerList.get(1));





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("dbError", "" + databaseError);

            }
        });



    }
}
