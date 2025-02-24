package com.nada.ecommerceapp.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.CartDB;

import java.util.ArrayList;

public class ListAdapter_order extends ArrayAdapter {
    Context adaptercontext;
    int adapterResource , userid;
    CartElement[] adapterCartElement;
    AdapterView.OnItemClickListener listener;
    private ArrayList<CartElement> courseModalArrayList;
    private Activity parentActivity;
    double sub;

    public ListAdapter_order(@NonNull Context context, int resource, CartElement[] cart_element_data, int userID ,  Activity activity) {
        super(context, resource, cart_element_data);
        adaptercontext = context;
        adapterResource = resource;
        adapterCartElement = cart_element_data;
        userid = userID;
        parentActivity = activity;

    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater rowInflater = LayoutInflater.from(adaptercontext);
        View row = rowInflater.inflate(adapterResource, parent, false);

        CartDB cartdb = new CartDB(adaptercontext);
        adapterCartElement = cartdb.getData(userid);

        TextView title_product = (TextView) row.findViewById(R.id.element_title);
        ImageView product_img = (ImageView) row.findViewById(R.id.element_img);
        CartElement element1 = adapterCartElement[position];
        title_product.setText(element1.getName());

        product_img.setImageURI(Uri.parse(element1.getImage()));
        TextView Product_count = (TextView) row.findViewById(R.id.element_quantity);
        Product_count.setText(String.valueOf(element1.getProduct_count()));
        TextView Product_total = (TextView) row.findViewById(R.id.element_total);
        Product_total.setText(String.valueOf(element1.getProduct_Price() * element1.getProduct_count()));

        return row;
    }
}
