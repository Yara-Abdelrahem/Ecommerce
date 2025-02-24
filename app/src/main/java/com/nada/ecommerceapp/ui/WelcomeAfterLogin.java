package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;

public class WelcomeAfterLogin extends AppCompatActivity {
    Button startShopping ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_after_login);
        startShopping = findViewById(R.id.start) ;
        startShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeAfterLogin.this , Categories.class);
                startActivity(intent);
            }
        });
    }
}