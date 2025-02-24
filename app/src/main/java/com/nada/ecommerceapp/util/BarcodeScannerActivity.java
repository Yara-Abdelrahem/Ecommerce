package com.nada.ecommerceapp.util;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nada.ecommerceapp.R;
import com.nada.ecommerceapp.repo.localData.DBHelper;


public class BarcodeScannerActivity extends AppCompatActivity {
    private IntentIntegrator barcodeScanner;
    private DBHelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
        } else {
            dBhelper= new DBHelper(this);
            barcodeScanner = new IntentIntegrator(this);
            barcodeScanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            barcodeScanner.setPrompt("Scan a barcode");
            barcodeScanner.setBeepEnabled(true);
            barcodeScanner.setOrientationLocked(false);
            barcodeScanner.initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // Barcode scanning was canceled
                // Handle accordingly
            } else {
                String barcode = result.getContents();
                // Handle the scanned barcode
                // Retrieve data from the database using the barcode value

                SQLiteDatabase db = dBhelper.getReadableDatabase();
                String[] projection = { "column1", "column2", "column3" }; // Replace with the actual column names you want to retrieve
                String selection = "barcode_column = ?";
                String[] selectionArgs = { barcode };

                Cursor cursor = db.query("your_table_name", projection, selection, selectionArgs, null, null, null);

                // Iterate over the cursor and do something with the retrieved data
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        String column1Value = cursor.getString(cursor.getColumnIndexOrThrow("column1"));
                        String column2Value = cursor.getString(cursor.getColumnIndexOrThrow("column2"));
                        String column3Value = cursor.getString(cursor.getColumnIndexOrThrow("column3"));

                        // Handle the retrieved data
                        // ...
                    } while (cursor.moveToNext());
                }

                // Close the cursor and database connection
                if (cursor != null) {
                    cursor.close();
                }
                db.close();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}