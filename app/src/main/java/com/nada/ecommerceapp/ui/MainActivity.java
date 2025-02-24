package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.UserDatabase;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler(Looper.getMainLooper());
    UserDatabase userDatabase = new UserDatabase(MainActivity.this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , LogOrSign.class);
                startActivity(intent);

            }
        },5000) ;
    }
}