package com.nada.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.DBHelper;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class Admin_Add_Update_Delete extends AppCompatActivity {

    EditText ID, Category, Amount, Descriebtion, name, price;
    Button Insert, Update, Delete ,Viewdetails;
    DBHelper DB;
    AnimatedBottomBar animatedBottomBar ;
    ImageView imageView;
    FloatingActionButton button;
    String url =" " ;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_update_delete);


        ID = findViewById(R.id.ID);
        Category = findViewById(R.id.Category);
        name = findViewById(R.id.NameProduct);
        Amount = findViewById(R.id.Amount);
        Descriebtion = findViewById(R.id.Descriebtion);
        price = findViewById(R.id.price);


        Insert = findViewById(R.id.btnInsert);
        Update = findViewById(R.id.btnUpdate);
        Delete =findViewById(R.id.btnDelete);

        DB= new DBHelper(this);
        boolean checkinsertdata =DB.insert_product_data("100","SHIRTS","item5","20" ,"DescriebtionTXT","501","jhgfdfghjkljhgcfcgvhbnjkl;");


        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDTXT =ID.getText().toString();
                String CategoryTXT =Category.getText().toString().toLowerCase();
                String nameTXT =name.getText().toString();
                String AmountTXT =Amount.getText().toString();
                String DescriebtionTXT =Descriebtion.getText().toString();
                String priceTXT =price.getText().toString();
                boolean checkinsertdata =DB.insert_product_data(IDTXT,CategoryTXT,nameTXT,AmountTXT ,DescriebtionTXT,priceTXT,url);
                if (checkinsertdata){
                    Toast.makeText(Admin_Add_Update_Delete.this, "New Product is added" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Admin_Add_Update_Delete.this, "New Product can't be added" , Toast.LENGTH_SHORT).show();

                }
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDTXT =ID.getText().toString();
                String CategoryTXT =Category.getText().toString();
                String nameTXT =name.getText().toString();
                String AmountTXT =Amount.getText().toString();
                String DescriebtionTXT =Descriebtion.getText().toString();
                String priceTXT =price.getText().toString();
                boolean checkupdatedata =DB.update_product_data(IDTXT,CategoryTXT,nameTXT , AmountTXT ,DescriebtionTXT,priceTXT,url);
                if (checkupdatedata){
                    Toast.makeText(Admin_Add_Update_Delete.this, "Product is Updated" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Admin_Add_Update_Delete.this, "Product can't be Updated" , Toast.LENGTH_SHORT).show();

                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDTXT =ID.getText().toString();
                // String CategoryTXT =Category.getText().toString();
                //String AmountTXT =Amount.getText().toString();
                // String DescriebtionTXT =Descriebtion.getText().toString();
                boolean checkdeleteddata =DB.delete_product_data(IDTXT);
                if (checkdeleteddata){
                    Toast.makeText(Admin_Add_Update_Delete.this, "Product is Deleted" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Admin_Add_Update_Delete.this, "Product can't be Deleted" , Toast.LENGTH_SHORT).show();

                }
            }
        });


        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.floatingActionButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Admin_Add_Update_Delete.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        animatedBottomBar = findViewById(R.id.bottom_bar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @androidx.annotation.Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                Intent intent ;
                switch (tab1.getId()){
                    case R.id.tab_home:
                        intent = new Intent(getApplicationContext() , AdminMain.class);
                        startActivity(intent);
                        break;
                    case R.id.tab_cart:
                        intent = new Intent(getApplicationContext() , Products.class);
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
        url = uri.toString();
    }
}