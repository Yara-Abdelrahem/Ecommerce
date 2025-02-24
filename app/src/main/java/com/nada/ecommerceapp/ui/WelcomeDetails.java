package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.util.SliderAdapter;

public class WelcomeDetails extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;

    int currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_details);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


            //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

            //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

            //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

        public void skip(View view) {
            Intent intent = new Intent(WelcomeDetails.this , SignUp.class) ;
            startActivity(intent);

        }

        public void next(View view) {
            currentPos += 1 ;
            viewPager.setCurrentItem(currentPos);
            if(currentPos == 3){
                skip(view);
            }
        }

        private void addDots(int position) {

            dots = new TextView[3];
            dotsLayout.removeAllViews();

            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(getResources().getColor(R.color.gray));
                dotsLayout.addView(dots[i]);
            }

            if (dots.length > 0) {
                dots[position].setTextColor(getResources().getColor(R.color.black));
            }

        }

        ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDots(position);
                currentPos =position ;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };


}