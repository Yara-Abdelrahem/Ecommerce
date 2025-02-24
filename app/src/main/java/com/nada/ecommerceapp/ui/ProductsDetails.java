package com.nada.ecommerceapp.ui;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.CartDB;
import com.nada.ecommerceapp.repo.localData.DBHelper;
import com.nada.ecommerceapp.repo.localData.UserDatabase;
import com.nada.ecommerceapp.util.CartElement;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class ProductsDetails extends AppCompatActivity {
    ListView comms;
    ArrayAdapter<String> adapter;
    DBHelper db;
    UserDatabase userDatabase ;
    ImageView imageView ;
    AnimatedBottomBar animatedBottomBar ;
    CartDB dbCart ;
    ImageButton imageButton ;
    EditText comment ;
    TextView pame , price ,desc ,rate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);
        imageView = findViewById(R.id.imageView2);
        pame = findViewById(R.id.pname);
        price = findViewById(R.id.price);
        desc = findViewById(R.id.descp);
        rate = findViewById(R.id.rate);
        String name = getIntent().getStringExtra("name" );
        String description = getIntent().getStringExtra("desc" );
        String prices = getIntent().getStringExtra("price" );
        String image = getIntent().getStringExtra("image" );
        imageView.setImageURI(Uri.parse(image));
        pame.setText(name);
        price.setText(prices);
        desc.setText(description);
        imageButton = findViewById(R.id.sends);
        comment = findViewById(R.id.commentedit) ;
        db = new DBHelper(getApplicationContext());
        userDatabase = new UserDatabase(this) ;
        dbCart = new CartDB(this);
        Cursor c = db.getComments(name);
        if(c.getCount() !=0 ) {
            comms = (ListView) findViewById(R.id.comments);
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
            comms.setAdapter(adapter);
            while (c.moveToNext()) {
                adapter.add(c.getString(1));

            }
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = comment.getText().toString();
                SharedPreferences preferences = getSharedPreferences("Userid",MODE_PRIVATE);
                int userid = parseInt(preferences.getString("userid", ""));
                Cursor c = userDatabase.getUsername(userid);
                c.moveToNext();
                if(c.getCount()>0) {
                    long res=  db.insert_comment(name, c.getString(0), Integer.parseInt(String.valueOf(userid)), body);
                    Toast.makeText(ProductsDetails.this, String.valueOf(res), Toast.LENGTH_SHORT).show();
                }
                c = db.getComments(name);
                if(c.getCount() !=0 ) {
                    comms = (ListView) findViewById(R.id.comments);
                    adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    comms.setAdapter(adapter);
                    while (c.moveToNext()) {
                        adapter.add(c.getString(1));

                    }
                }
            }
        });
        Button AddToCart = findViewById(R.id.cartB);
        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.getid_amount(description,name,prices);
                c.moveToNext();
                SharedPreferences preferences = getSharedPreferences("Userid",MODE_PRIVATE);
                int userid = parseInt(preferences.getString("userid", ""));
                CartElement elementlist = new CartElement(userid,name,Integer.parseInt(c.getString(0)), 1
                        ,Double.parseDouble( prices) , Double.parseDouble( prices)  ,userid,image);
                dbCart.insertElement(elementlist);
                open_cart( c.getString(0), c.getString(1));
            }
        });

        animatedBottomBar = findViewById(R.id.bottom_bar);

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                Intent intent ;
                switch (tab1.getId()){
                    case R.id.tab_home:
                        intent = new Intent(ProductsDetails.this, Categories.class);
                        finish();
                        startActivity(intent);
                        break;
                    case R.id.tab_cart:
                        intent = new Intent(getApplicationContext() , Cart.class);
                        finish();
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
    private void open_cart(String ProductID, String amount) {
        Intent intent = new Intent(this , Cart.class);
        intent.putExtra("ProductID" ,ProductID);
        intent.putExtra("Product_amount", amount);
        startActivity(intent);
    }
}