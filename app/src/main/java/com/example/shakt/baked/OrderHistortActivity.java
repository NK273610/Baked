package com.example.shakt.baked;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class OrderHistortActivity extends AppCompatActivity {
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_histort);
        toolbar = (Toolbar) findViewById(R.id.orderHistory_toolbar);
        toolbar.setTitle(getString(R.string.order_history));

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
