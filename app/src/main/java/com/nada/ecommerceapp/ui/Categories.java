package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Categories extends AppCompatActivity {
    private Button shirtsButton;
    private Button jacketsButton;
    private Button dressButton;
    private Button blouseButton;
    private Button trousersButton;
    private Button skirtsButton;
    AnimatedBottomBar animatedBottomBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        int count = 0 ;
        shirtsButton = findViewById(R.id.shirt_button);
        jacketsButton = findViewById(R.id.Jackets_button3);
        trousersButton = findViewById(R.id.Trousers_button4);
        skirtsButton = findViewById(R.id.skirts_button2);
        dressButton = findViewById(R.id.Dress_button5);
        blouseButton= findViewById(R.id.Blouses_button6);
        animatedBottomBar = findViewById(R.id.bottom_bar);
        final int shirt_ID = 1;
        final int jacket_ID = 2;
        final int trouser_ID = 3;
        final int skirt_ID = 4;
        final int dress_ID = 5;
        final int blouse_ID = 6;

        shirtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(Categories.this, Products.class);
                intent.putExtra("category_id", shirt_ID);
                startActivity(intent);

            }
        });
        jacketsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(Categories.this, Products.class);
                intent.putExtra("category_id", jacket_ID);
                startActivity(intent);

            }
        });
        trousersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(Categories.this, Products.class);
                intent.putExtra("category_id", trouser_ID);
                startActivity(intent);

            }
        });
        skirtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(Categories.this, Products.class);
                intent.putExtra("category_id", skirt_ID);
                startActivity(intent);

            }
        });
        dressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(Categories.this, Products.class);
                intent.putExtra("category_id", dress_ID);
                startActivity(intent);

            }
        });
        blouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(Categories.this, Products.class);
                intent.putExtra("category_id", blouse_ID);
                startActivity(intent);

            }
        });

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                Intent intent ;
                switch (tab1.getId()){
                    case R.id.tab_home:
                        break;
                    case R.id.tab_cart:
                        intent = new Intent(getApplicationContext() , Cart.class);
                        startActivity(intent);
                        break;
                    case R.id.tab_logout:
                        SharedPreferences preferences = getSharedPreferences("checkedbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();
                        intent = new Intent(getApplicationContext() , LogOrSign.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }


}