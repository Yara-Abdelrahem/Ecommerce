package com.nada.ecommerceapp.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.DBHelper;

public class Order extends AppCompatActivity {
    ListView orders;
    ArrayAdapter<String> adapter;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orders=(ListView) findViewById(R.id.feedBack);
        adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        orders.setAdapter(adapter);
        db= new DBHelper(getApplicationContext());
        Cursor c=db.getdata_comment();
        if (c.getCount() !=0){
            c.moveToFirst();
            int ProductId = c.getInt(0);
            Toast.makeText(this,"ProductId " +String.valueOf(ProductId)+ " No. of comment" + String.valueOf(c.getCount()), Toast.LENGTH_SHORT).show();
            c.close();

        }
    }
}