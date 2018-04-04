package com.example.shakt.baked;


import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import android.widget.Toast;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RecommendationActivity extends AppCompatActivity {
  
    String value;
    InfoClass fragInfo;
    ImageView img;
    public static final String FIREBASE_CHILD_PRODUCTS = "Products";
    Product_Class obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
         value= intent.getStringExtra("Strain");
        Bundle bundle = new Bundle();
        bundle.putString("strain", value );
        fragInfo = new InfoClass();
        fragInfo.setArguments(bundle);
        Log.e(RecommendationActivity.class.getCanonicalName(), value);

        img=findViewById(R.id.img);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Review"));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FIREBASE_CHILD_PRODUCTS);

        myRef = myRef.child("Blue Dream");
       obj=new Product_Class();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    obj = dataSnapshot.getValue(Product_Class.class);

                }
                Picasso.get().load(obj.getProductPic()).into(img);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }
    class PagerAdapter extends FragmentStatePagerAdapter {

        int numberOfTabs;


        public PagerAdapter(FragmentManager fm, int numberOfTabs){
            super(fm);
            this.numberOfTabs = numberOfTabs;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position){
                case 0:
                    return fragInfo;
                case 1:
                    return new ReviewClass();


           }


            return null;

        }

        @Override
        public int getCount() {
            return numberOfTabs;
        }
    }
    }

