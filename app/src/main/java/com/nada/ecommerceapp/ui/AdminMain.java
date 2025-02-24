package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.DBHelper;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class AdminMain extends AppCompatActivity {
    private Button Viewdetails;
    private Button chart;
    private Button feedback;
    private Button report;
    private Button manage;
    DBHelper DB;
    AnimatedBottomBar animatedBottomBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Viewdetails =findViewById(R.id.btnViewdetails);
        chart = findViewById(R.id.charts);
        feedback = findViewById(R.id.feedback);
        report = findViewById(R.id.report);
        manage= findViewById(R.id.manage);

        DB= new DBHelper(this);
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(getApplicationContext(), Chart.class);
                finish();
                startActivity(intent);

            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(getApplicationContext(), Order.class);
                finish();
                startActivity(intent);

            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the ShirtsActivity
                Intent intent = new Intent(getApplicationContext(), Admin_Add_Update_Delete.class);
                finish();
                startActivity(intent);

            }
        });

        Viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Cursor res =DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(AdminMain.this, "No product Exsists" , Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID :"+res.getString(0) + "\n");
                    buffer.append("Category :"+res.getString(1)+"\n");
                    buffer.append("Name :"+res.getString(2) + "\n");
                    buffer.append("Amount :" +res.getString(3)+"\n");
                    buffer.append("Description :"+res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminMain.this);
                builder.setCancelable(true);
                builder.setTitle("Product info");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        animatedBottomBar = findViewById(R.id.bottom_bar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                Intent intent ;
                switch (tab1.getId()){
                    case R.id.tab_home:
                        break;
                    case R.id.tab_cart:
                        intent = new Intent(getApplicationContext() , Products.class);
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