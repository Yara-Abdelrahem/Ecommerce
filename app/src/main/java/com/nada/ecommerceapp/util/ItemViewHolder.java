package com.nada.ecommerceapp.util;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nada.ecommerceapp.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView priceTextView;
    public TextView descriptionTextView;
    public ImageView imageView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        priceTextView = itemView.findViewById(R.id.price);
        imageView = itemView.findViewById(R.id.imageViews);

    }


}
