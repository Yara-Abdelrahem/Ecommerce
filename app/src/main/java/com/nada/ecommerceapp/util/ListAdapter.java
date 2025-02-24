package com.nada.ecommerceapp.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.CartDB;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {
     Context adaptercontext;
     int adapterResource ,Product_amount ;
     CartElement[] adapterCartElement;
     AdapterView.OnItemClickListener listener;
    private ArrayList<CartElement> courseModalArrayList;
    private Activity parentActivity;
    double sub ;
    public ListAdapter(@NonNull Context context, int resource , CartElement[] cart_element_data , int amount , Activity activity) {
        super(context, resource, cart_element_data);
        adaptercontext=context;
        adapterResource = resource;
        adapterCartElement = cart_element_data;
        parentActivity = activity;
        Product_amount=amount;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater rowInflater =LayoutInflater.from(adaptercontext);
        View row = rowInflater.inflate(adapterResource,parent,false);

        TextView title_product = (TextView) row.findViewById(R.id.element_title);
        TextView description_product = (TextView) row.findViewById(R.id.element_detail);
        ImageView product_img = (ImageView) row.findViewById(R.id.element_img);
        CartElement element1 = adapterCartElement[position];
        title_product.setText(element1.getName());
        description_product.setText(element1.getDetails());

        product_img.setImageURI(Uri.parse(element1.getImage()));
        TextView Product_count = (TextView) row.findViewById(R.id.element_quantity);
        Product_count.setText( String.valueOf(element1.getProduct_count()));
        TextView Product_price = (TextView) row.findViewById(R.id.element_price);
        Product_price.setText( String.valueOf(element1.getProduct_Price()));
        TextView Product_total = (TextView) row.findViewById(R.id.element_total);
        Product_total.setText( String.valueOf(element1.getProduct_Price() *element1.getProduct_count() ));
        CartDB cartdb = new CartDB(adaptercontext);


        Button plus_btn = row.findViewById(R.id.button_more);
        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (element1.getProduct_count()+1<Product_amount){
                    element1.setProduct_count( element1.getProduct_count()+1);
                    Product_count.setText(String.valueOf(element1.getProduct_count()));
                    Product_total.setText( String.valueOf(element1.getProduct_Price() *element1.getProduct_count() ));
                    String res= cartdb.updateCart(element1);
                    if (res == "Done"){
                        TextView cart_subtotal = parentActivity.findViewById(R.id.cart_subtotal_num);
                        sub = Double.parseDouble(cart_subtotal.getText().toString()) + element1.getProduct_Price();
                        cart_subtotal.setText(String.valueOf(sub));
                        TextView total_txt = parentActivity.findViewById(R.id.cart_total_num) ;
                        total_txt.setText(String.valueOf(sub+35));
                        parentActivity.finish();
                        parentActivity.startActivity(parentActivity.getIntent());
                        Toast.makeText(adaptercontext, "Incremented sucessfully :) ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(adaptercontext, "Sorry we don't have more :( ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Button minus_btn = row.findViewById(R.id.button_less);
        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (element1.getProduct_count()> 1){
                    element1.setProduct_count( element1.getProduct_count()-1);
                    Product_count.setText(String.valueOf(element1.getProduct_count()));
                    Product_total.setText( String.valueOf(element1.getProduct_Price() *element1.getProduct_count() ));
                    String res =cartdb.updateCart(element1);
                    if (res == "Done"){
                        TextView cart_subtotal = parentActivity.findViewById(R.id.cart_subtotal_num);
                         sub = Double.parseDouble(cart_subtotal.getText().toString()) - element1.getProduct_Price();
                        cart_subtotal.setText(String.valueOf(sub) );
                        TextView total_txt = parentActivity.findViewById(R.id.cart_total_num) ;
                        total_txt.setText(String.valueOf(sub+35));
                        parentActivity.finish();
                        parentActivity.startActivity(parentActivity.getIntent());
                        Toast.makeText(adaptercontext, "Decremented sucessfully ", Toast.LENGTH_SHORT).show();
                    }
                } else if (element1.getProduct_count() == 1) {
                    Toast.makeText(adaptercontext, "It 'll be Deleted ", Toast.LENGTH_SHORT).show();
                    cartdb.deleteElement(element1);
                    parentActivity.finish();
                    parentActivity.startActivity(parentActivity.getIntent());
                    Toast.makeText(adaptercontext, "Deleted Sucessfully ", Toast.LENGTH_SHORT).show();

                }
            }
        });



        return row;
    }
}

