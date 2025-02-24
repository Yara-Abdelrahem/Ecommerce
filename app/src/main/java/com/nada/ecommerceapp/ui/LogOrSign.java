package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;

public class LogOrSign extends AppCompatActivity {
    Button logIn , signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_or_sign);
        logIn = findViewById(R.id.login) ;
        signUp = findViewById(R.id.signup) ;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogOrSign.this , WelcomeDetails.class) ;
                startActivity(intent);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogOrSign.this , Login.class) ;
                startActivity(intent);
            }
        });
    }
}