package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.CartDB;
import com.nada.ecommerceapp.util.CartElement;
import com.nada.ecommerceapp.util.ListAdapter_order;
import com.nada.ecommerceapp.util.Order_confirmation;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Check_out extends AppCompatActivity {
    TextView total_checkout ;
    EditText country_text  , city_text , Street_text,phone_number_text , zipcode_text,email_text;
    ListView confirm_cart;
    Button save_address , place_order_btn ;
    int xml_file = R.layout.element_checkout;
    CartDB db = new CartDB(this);
    Order_confirmation order;
    boolean address_enterted ;
    AnimatedBottomBar animatedBottomBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        double total = getIntent().getDoubleExtra("Total", 100.0);
        int element_count = getIntent().getIntExtra("element_count", 1);
        int userid = getIntent().getIntExtra("userid", 1);
        int cartid = getIntent().getIntExtra("cartid", 1);
        total_checkout = findViewById(R.id.total_checkout);
        total_checkout.setText(String.valueOf(total));
        confirm_cart = (ListView) findViewById(R.id.confirmcart_list);
        CartElement[] orderlist = new CartElement[element_count];
        ListAdapter_order adapter = new ListAdapter_order(this, xml_file, orderlist, userid, this);
        confirm_cart.setAdapter(adapter);
        country_text = findViewById(R.id.country_text);
        city_text = findViewById(R.id.city_text);
        Street_text = findViewById(R.id.Street_text);
        phone_number_text = findViewById(R.id.phone_number_text);
        zipcode_text = findViewById(R.id.zipcode_text);
        email_text = findViewById(R.id.email_text);


        save_address =(Button) findViewById(R.id.save_address_btn);

        save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count =0 ;
                String country = country_text.getText().toString();
                String city = city_text.getText().toString();
                String Street = Street_text.getText().toString();
                String  phone_number= phone_number_text.getText().toString();
                String zip_code =zipcode_text.getText().toString();
                String email = email_text.getText().toString();
                if (android.text.TextUtils.isEmpty(city)){
                    city_text.setError("Required!");
                }else{
                    count+=1;
                }
                if (android.text.TextUtils.isEmpty(Street)){
                    Street_text.setError("Required!");
                }  else{
                    count+=1;
                }
                if (android.text.TextUtils.isEmpty(email)){
                    email_text.setError("Required!");
                } else{
                    count+=1;
                }
                if (android.text.TextUtils.isEmpty(phone_number)){
                    phone_number_text.setError("Required!");
                }else{
                    count+=1;
                }
                if (android.text.TextUtils.isEmpty(zip_code)){
                    zipcode_text.setError("Required!");
                }else{
                    count+=1;
                }
                if (count==5){
                    order = new Order_confirmation(userid, cartid, phone_number, zip_code, total, country, city, Street, email, orderlist);
                    Toast.makeText(Check_out.this, "Your Address is saved :)", Toast.LENGTH_SHORT).show();
                    address_enterted =true;
                }else{
                    address_enterted =false;
                    Toast.makeText(Check_out.this, "Please fill with your data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        place_order_btn = findViewById(R.id.place_order_btn);
        place_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (address_enterted==true){
                    Toast.makeText(Check_out.this, "Thank you for choosing us :)", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Check_out.this, "Please fill with your data", Toast.LENGTH_SHORT).show();

                }
            }
        });
        animatedBottomBar = findViewById(R.id.bottom_bar);

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                Intent intent ;
                switch (tab1.getId()){
                    case R.id.tab_home:
                        intent = new Intent(getApplicationContext() , Categories.class);
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
}