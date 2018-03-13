package com.example.shakt.baked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.file.Files;

public class HomeActivity extends AppCompatActivity {

    private Button btnMapActivity;
    private Button useraccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnMapActivity = findViewById(R.id.map_button);
        useraccount = findViewById(R.id.btn_useraccount);

        btnMapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);

            }
        });
        useraccount.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(getApplicationContext(),UserAccount.class);
                                               startActivity(intent);
                                           }
                                       }
        );

    }
}
