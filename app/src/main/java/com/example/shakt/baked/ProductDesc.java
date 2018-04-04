package com.example.shakt.baked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    RecyclerView rv;
    ProductAdapter pa;
    List<String> productHandlerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_desc);
        Intent intent = getIntent();
        String typeProd = intent.getStringExtra("type");
        final String[] prodName = new String[1];
        rv= findViewById(R.id.product_recycler);
        productHandlerList = new ArrayList<>();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);


        Toast.makeText(getApplicationContext(), typeProd, Toast.LENGTH_SHORT).show();
        productHandler = new Product_Class();
        Log.e("New TAg",typeProd);
        typeReference = FirebaseDatabase.getInstance().getReference("Type").child(typeProd);

        typeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                productHandlerList = (ArrayList<String>) dataSnapshot.getValue();
                Log.e("Here", "" + productHandlerList.get(1));
                pa=new ProductAdapter(getApplicationContext(),productHandlerList);
                rv.setAdapter(pa);
                pa.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("dbError", "" + databaseError);

            }

        });



    }
}
