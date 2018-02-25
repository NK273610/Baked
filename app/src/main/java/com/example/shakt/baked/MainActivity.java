package com.example.shakt.baked;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText user_name_input;
    private EditText password_input;
    private Button loginButton;
    private Button anonymousButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name_input = findViewById(R.id.user_name_input);
        password_input = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.loginButton);
        anonymousButton = findViewById(R.id.anonymousButton);
        auth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_name_input.getText().toString().equals("nikhildhirmalani@gmail.com") &&
                        password_input.getText().toString().equals("nikhil1994"))
                {

                    auth.createUserWithEmailAndPassword(user_name_input.getText().toString(), password_input.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), task.getException().toString(),Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
