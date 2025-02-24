package com.nada.ecommerceapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.DBHelper;
import com.nada.ecommerceapp.util.BarcodeScannerActivity;
import com.nada.ecommerceapp.util.Item;
import com.nada.ecommerceapp.util.ItemAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Products extends AppCompatActivity {
    private RecyclerView itemsRecyclerView;
    public ItemAdaptor itemAdaptor;
    private ImageButton scanBarcodeButton;
    private List<Item> items;
    private DBHelper dBhelper;
    private static final int SPEECH_REQUEST_CODE = 1;
    private static final int BARCODE_SCAN_REQUEST_CODE = 2; // Choose a unique code for barcode scanning
    TextView title ;
    AnimatedBottomBar animatedBottomBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        dBhelper = new DBHelper(this);
        title = findViewById(R.id.title_text);

        int categoryId = getIntent().getIntExtra("category_id", -1);
        String categoryType = "";
        if (categoryId != -1) {

            switch (categoryId){
                case 1:
                    categoryType = "Shirts";
                    break;
                case 2:
                    categoryType = "Jackets";
                    break;
                case 3:
                    categoryType = "Trousers";
                    break;
                case 4:
                    categoryType = "Skirts";
                    break;
                case 5:
                    categoryType = "Dresses";
                    break;
                case 6:
                    categoryType = "Blouses";
                    break;
            }
            title.setText(categoryType);
            // Retrieve items from database based on category ID
            items = getItemsByCategory(categoryType );
            // Initialize RecyclerView and adapter
            itemsRecyclerView = findViewById(R.id.recycler_view);
            itemAdaptor = new ItemAdaptor(this, items);
            itemsRecyclerView.setLayoutManager(new GridLayoutManager(Products.this,2));
            itemsRecyclerView.setAdapter(itemAdaptor);


        } else {
            // Handle invalid category ID
            Toast.makeText(this, "Invalid category ID", Toast.LENGTH_SHORT).show();
        }
        
        androidx.appcompat.widget.SearchView searchViewText = findViewById(R.id.search_view);
        searchViewText.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Filter items based on query
                // ...
                searchItems(query);
                return false;
           }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter items based on new text
                // ...
                searchItems(newText);
                return false;
            }
        });


        ImageButton voiceSearchButton = findViewById(R.id.voice_search_button);
        voiceSearchButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                startVoiceRecognition();
            }
        });
        animatedBottomBar = findViewById(R.id.bottom_bar);

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @SuppressLint("NonConstantResourceId")
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
    public List<Item> getItemsByCategory(String categoryType) {
        // Implement logic to retrieve items from database based on category ID
        // ... replace this with actual database interaction code ...
        Cursor cursor = dBhelper.getItemsByCategory(categoryType.toLowerCase());
        List<Item> items = new ArrayList<>();
        Item item ;
        while(cursor.moveToNext()) {
            item = new Item(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3)) ;
            items.add(item);
        }

        return items;

    }


    private void searchItems(String query) {

        SQLiteDatabase db = dBhelper.getReadableDatabase();
        String[] projection = { "column1", "column2", "column3" }; // Replace with the actual column names you want to retrieve
        String selection = "column1 LIKE ?";
        String[] selectionArgs = { "%" + query + "%" };

        Cursor cursor = db.query("your_table_name", projection, selection, selectionArgs, null, null, null);

        // Iterate over the cursor and do something with the retrieved data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String column1Value = cursor.getString(cursor.getColumnIndexOrThrow("column1"));
                String column2Value = cursor.getString(cursor.getColumnIndexOrThrow("column2"));
                String column3Value = cursor.getString(cursor.getColumnIndexOrThrow("column3"));

                // Handle the retrieved data, e.g., display it in a ListView or RecyclerView
                // ...
            } while (cursor.moveToNext());
        }

        // Close the cursor and database connection
        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }
//    public Cursor getProd(String[] args){
//        SQLiteDatabase db = getReadableDatabase();
//        SearchView search=findViewById(R.id.search_view);
//        String[] q={search.getQuery().toString()};
//        args=q;
//        String[] attributes={"Category","Descriebtion"};
//        Cursor c=db.query("ProductDetails",attributes,"WHERE Name =?",args,null,null,null,null);
//        if(c!=null)
//            c.moveToFirst();
//        db.close();
//       return c;
//    }
    // ...
    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your search query...");

        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && !results.isEmpty()) {
                String query = results.get(0);
                Cursor c = dBhelper.getprodustsDetailsforSearch(query) ;
                if(c.getCount()>0) {
                    List<Item> items = new ArrayList<Item>();
                    Item item;
                    while (c.moveToNext()) {
                        item = new Item(c.getString(0), c.getString(1),
                                c.getString(2), c.getString(3));
                        items.add(item);
                    }
                    itemAdaptor = new ItemAdaptor(this, items);
                    itemsRecyclerView.setAdapter(itemAdaptor);
                    itemsRecyclerView.setLayoutManager(new GridLayoutManager(Products.this, 2));
                }

            }
        } else if (requestCode == BARCODE_SCAN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Barcode scanning was successful
                String barcode = data.getStringExtra("barcode");
                // Handle the scanned barcode
                // ...
            } else if (resultCode == RESULT_CANCELED) {
                // Barcode scanning was canceled
                // Handle accordingly
            }
        }

        scanBarcodeButton = findViewById(R.id.scan_barcode_button);
        scanBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBarcodeScanner();
            }
        });
    }
    private void startBarcodeScanner() {
        Intent intent = new Intent(Products.this, BarcodeScannerActivity.class);
        startActivityForResult(intent, BARCODE_SCAN_REQUEST_CODE);
    }
}