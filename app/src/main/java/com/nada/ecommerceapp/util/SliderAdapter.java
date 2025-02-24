package com.nada.ecommerceapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.nada.ecommerceapp.R;

public class SliderAdapter extends PagerAdapter {
    Context context ;
    LayoutInflater layoutInflater ;
    public SliderAdapter(Context context) {
        this.context = context;
    }
    int[] image  = {
            R.drawable.welcome ,R.drawable.welcome2 , R.drawable.welcome3
    } ;
    int []headings  = {
       R.string.first_slide_title , R.string.second_slide_title , R.string.third_slide_title
    } ;
    int description = R.string.slide_desc;
    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = layoutInflater.inflate(R.layout.welcome_details , container , false) ;
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);
        imageView.setImageResource(image[position]);
        heading.setText(headings[position]);
        desc.setText(description);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
