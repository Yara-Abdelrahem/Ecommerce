package com.nada.ecommerceapp.ui;


import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.databinding.ActivityCartBinding;
import com.nada.ecommerceapp.databinding.CartElementBinding;
import com.nada.ecommerceapp.repo.localData.CartDB;
import com.nada.ecommerceapp.util.CartElement;
import com.nada.ecommerceapp.util.ListAdapter;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Cart extends AppCompatActivity {
    ActivityCartBinding binding;
    CartElementBinding binding2;
    double Total_cost;
    int userid,  cartid ;
    CartDB db = new CartDB(this);
    AnimatedBottomBar animatedBottomBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        int ProductID = getIntent().getIntExtra("ProductID", 0);
        int Product_amount = getIntent().getIntExtra("Product_amount", 1);
        ListView ele_list = (ListView) findViewById(R.id.element_list);
        int xml_file = R.layout.cart_element;
        SharedPreferences preferences = getSharedPreferences("Userid",MODE_PRIVATE);
        userid = parseInt(preferences.getString("userid", ""));
        cartid  = userid ;

        TextView sub_txt;
        TextView shipping_txt;
        TextView total_txt;
        Button check_out_btn = findViewById(R.id.btn_checkout);
        CartElement[] elementlist = db.getData(userid);
        if (elementlist != null){
            ListAdapter adapter1 = new ListAdapter(Cart.this,xml_file, elementlist, Product_amount,this);
            ele_list.setAdapter(adapter1);
            Toast.makeText(this, "You have "+String.valueOf(elementlist.length) + " item in your cart", Toast.LENGTH_SHORT).show();

            int subtotal =0;
            for (CartElement cartElement : elementlist) {
                subtotal += cartElement.getProduct_total();
                sub_txt = findViewById(R.id.cart_subtotal_num);
                sub_txt.setText(String.valueOf(subtotal));
                shipping_txt = findViewById(R.id.cart_shipping_num);
                shipping_txt.setText(String.valueOf(35));
                total_txt = findViewById(R.id.cart_total_num);
                Total_cost = subtotal + 35;
                total_txt.setText(String.valueOf(Total_cost));
            }
        }else {
            Toast.makeText(this, "You Don't have any item in your cart", Toast.LENGTH_SHORT).show();
        }
        check_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elementlist.length != 0){
                    open_checkout(Total_cost, elementlist.length, userid , cartid);
                }else {
                    Toast.makeText(Cart.this, "Please Put Product , so you can checkout", Toast.LENGTH_SHORT).show();
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
    private void open_checkout(double total ,int element_count, int userid , int cartid) {
        Intent intent = new Intent(this , Check_out.class);
        intent.putExtra("Total" ,total);
        intent.putExtra("element_count" ,element_count);
        intent.putExtra("userid" , userid);
        intent.putExtra("cartid", cartid);
        startActivity(intent);
    }
}