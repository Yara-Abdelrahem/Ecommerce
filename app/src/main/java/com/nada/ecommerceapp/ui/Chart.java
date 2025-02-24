package com.nada.ecommerceapp.ui;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.CartDB;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {
    PieChart pieChart ;
    CartDB cartDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        cartDB = new CartDB(this);
        pieChart = findViewById(R.id.chart);
        Cursor cursor =  cartDB.gethigh_amount();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0 ; i < cursor.getCount() ; i++ ){
            cursor.moveToNext();

            PieEntry pieEntry = new PieEntry(Float.parseFloat( cursor.getString(1)),cursor.getString(0));
            pieEntries.add(pieEntry);
        }
        PieDataSet pie = new PieDataSet(pieEntries,"High products");
        pie.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(new PieData(pie));
        pieChart.animateXY(5000,5000);
        pieChart.getDescription().setEnabled(false);
    }
}