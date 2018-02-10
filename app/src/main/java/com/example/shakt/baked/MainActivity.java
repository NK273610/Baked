package com.example.shakt.baked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMapActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMapActivity = findViewById(R.id.map_button);

        btnMapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);

            }
        });
    }
}
