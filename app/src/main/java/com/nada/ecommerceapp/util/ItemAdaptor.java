package com.nada.ecommerceapp.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.ui.ProductsDetails;

import java.util.List;

public class ItemAdaptor extends RecyclerView.Adapter<ItemViewHolder> {
//    Item item = items.get(position);
//    Intent intent = new Intent( getApplicationContext(), ProductsDetails.class) ;
//                    intent.putExtra("name" , item.getName());
//                    intent.putExtra("desc" , item.getDescription());
//                    intent.putExtra("price" , item.getPrice());
//                    intent.putExtra("image" , item.getImageUrl());
//    startActivity(intent);
    private List<Item> items;
    private Context context;
    private OnClickListener onClickListener;
    public ItemAdaptor(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemadaptor, parent, false);
       ItemViewHolder itemViewHolder = new ItemViewHolder(itemView);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);
        // Set item data to the view holder
        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());
        holder.priceTextView.setText(item.getPrice());
        // ... Set other item properties to views ...
        holder.imageView.setImageURI(Uri.parse(item.getImageUrl()));
        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context , ProductsDetails.class);

            intent.putExtra("name" , items.get(position).getName());
            intent.putExtra("desc" ,  items.get(position).getDescription());
            intent.putExtra("price" , items.get(position).getPrice());
            intent.putExtra("image" ,    items.get(position).getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Item model);
    }
}