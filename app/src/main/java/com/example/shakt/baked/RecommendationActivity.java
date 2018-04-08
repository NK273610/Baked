package com.example.shakt.baked;


import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import android.widget.TextView;
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

    //variables used in the class declared
    String strain_value;//string strain value
    InfoClass fragInfo; //infoclass obj
    ImageView imgview;
    public static final String FIREBASE_CHILD_PRODUCTS = "Products";
    Product_Class Data_obj;//product class object
    ReviewClass reviewClass;//review class object
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent.getStringExtra("Strain")!=null) { //we take the intent value
            strain_value = intent.getStringExtra("Strain");//if the respective values are not null
        }
        if(intent.getStringExtra("OrderHistory")!=null) { //orderhistory value
            strain_value = intent.getStringExtra("OrderHistory");
        }
        if(intent.getStringExtra("favorite")!=null) {
            strain_value = intent.getStringExtra("favorite");//favourite value
        }
        if(intent.getStringExtra("Review")!=null) {
            strain_value = intent.getStringExtra("Review");//review value
        }
        if(intent.getStringExtra("search")!=null) {
            strain_value = intent.getStringExtra("search");//search value
        }
        Bundle bundle = new Bundle(); //we take the bundle
        bundle.putString("strain", strain_value );//we add the strain value
        fragInfo = new InfoClass();
        fragInfo.setArguments(bundle);//we set the arguments
        Bundle bundle1 = new Bundle();//create new bunble
        bundle1.putString("strainName", strain_value );//add strain value
        reviewClass = new ReviewClass();//create review class object
        reviewClass.setArguments(bundle1);//set the arguments

//        Log.e(RecommendationActivity.class.getCanonicalName(), value);

        imgview=findViewById(R.id.img); //get the imageview id
        textview=findViewById(R.id.myImageViewText);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout); //get tablayout id
        tabLayout.addTab(tabLayout.newTab().setText("Info"));//we set the texts for info and review
        tabLayout.addTab(tabLayout.newTab().setText("Review"));
        FirebaseDatabase database = FirebaseDatabase.getInstance(); //create fire base object
        DatabaseReference myRef = database.getReference(FIREBASE_CHILD_PRODUCTS); //get reference to our database

//        myRef = myRef.child("Blue Dream");
        myRef = myRef.child(strain_value);//we go to particular strain
        Data_obj=new Product_Class();//data object  created

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    Data_obj = dataSnapshot.getValue(Product_Class.class);//data object has all values from firebase

                }
                Picasso.get().load(Data_obj.getProductPic()).into(imgview);//using picaso to set image from url
                textview.setText(Data_obj.getProductInfo());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager); //view pager code
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

        int numberOfTabs; //no of tabs


        public PagerAdapter(FragmentManager fm, int numberOfTabs){
            super(fm);
            this.numberOfTabs = numberOfTabs;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position){
                case 0:
                    return fragInfo; //fraginto class object
                case 1:
                    return reviewClass; //review class object
            }

            return null;

        }

        @Override
        public int getCount() {
            return numberOfTabs;
        }
    }
    }

