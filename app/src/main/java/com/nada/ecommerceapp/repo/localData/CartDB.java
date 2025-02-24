package com.nada.ecommerceapp.repo.localData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nada.ecommerceapp.util.CartElement;

public class CartDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "cart_table";
    public static final String CART_CL1 = "ID";
    public static final String CART_CL2 = "CartID";
    public static final String CART_CL3 = "Product_name";
    public static final String CART_CL4 = "Product_ID";
    public static final String CART_CL5 = "Product_quantity";
    public static final String CART_CL6 = "Product_price";
    public static final String CART_CL7 = "Product_total";
    public static final String CART_CL8 = "UserID";
    public static final int DB_VERSION = 5;

    public CartDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTableQuery = "CREATE TABLE " + DB_NAME + " (" +
//                CART_CL1 + " INTEGER PRIMARY KEY, " +
//                CART_CL2 + " INTEGER, " +
//                CART_CL3 + " TEXT, " +
//                CART_CL4 + " INTEGER, " +
//                CART_CL5 + " INTEGER, " +
//                CART_CL6 + " REAL, " +
//                CART_CL7 + " REAL, " +
//                CART_CL8 + " INTEGER)";

        db.execSQL("create table cart_table (ID INTEGER primary key AUTOINCREMENT, CartID INTEGER,Product_ID INTEGER, Product_name TEXT, Product_quantity INTEGER, Product_price REAL, Product_total REAL, UserID INTEGER, Product_img TEXT)");
//        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);

    }

    public String insertElement(CartElement element) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CartID", element.getCartID());
        values.put("Product_ID" , element.getProduct_ID());
        values.put("Product_name", element.getName());
        values.put("Product_quantity", element.getProduct_count());
        values.put("Product_price", element.getProduct_Price());
        values.put("Product_total", element.getProduct_count() * element.getProduct_Price());
        values.put("UserID", element.getUserID());
        values.put("Product_img" , element.getImage());
        long res = db.insert(DB_NAME, null, values);
        if (res==-1){
            return "Failed";
        }
        else{
            return "Done";
        }
    }
    public String updateCart(CartElement element) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CartID", element.getCartID());
        values.put("Product_ID" , element.getProduct_ID());
        values.put("Product_name", element.getName());
        values.put("Product_quantity", element.getProduct_count());
        values.put("Product_price", element.getProduct_Price());
        values.put("Product_total", element.getProduct_count() * element.getProduct_Price());
        values.put("UserID", element.getUserID());
        values.put("Product_img",element.getImage());

        int res = db.update(DB_NAME, values,  "Product_ID=? AND UserID =?", new String[]{String.valueOf(element.getProduct_ID()) , String.valueOf(element.getUserID())});
        db.close();
        if (res > 0){
            return "Done";
        }else{
            return "Failed";
        }

    }
    public CartElement[] getData(int UserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM cart_table WHERE UserID = ?", new String[] {String.valueOf(UserId)});
            int count = cursor.getCount();
            if (count == 0) {
                return null;
            }
            CartElement[] elementList = new CartElement[count];
            int i = 0;
            if (cursor.moveToFirst()) {
                do {
                    int cartId = cursor.getInt(1);
                    String name = cursor.getString(3);
                    int productId = cursor.getInt(2);
                    int productCount = cursor.getInt(4);
                    double price = cursor.getDouble(5);
                    int userId = cursor.getInt(7);
                    String img = cursor.getString(8);
                    CartElement element = new CartElement(cartId, name, productId, productCount, price,  productCount* price, userId,img);
                    elementList[i] = element;
                    i += 1;
                } while (cursor.moveToNext());
            }
            return elementList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    public String deleteElement(CartElement element) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(DB_NAME,  "Product_ID=? AND UserID =?", new String[]{String.valueOf(element.getProduct_ID()) , String.valueOf(element.getUserID())});
        if (res > 0){
            return "Done";
        }else{
            return "Failed";
        }
    }

    public Cursor gethigh_amount()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor =  DB.rawQuery("Select Product_name , Product_quantity from cart_table order by Product_quantity desc limit 10 " , null);
        return cursor;
    }

}