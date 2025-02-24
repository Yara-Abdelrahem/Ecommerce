package com.nada.ecommerceapp.repo.localData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "ProductData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table ProductDetails (ID TEXT primary key , Category TEXT ,Name TEXT, Amount TEXT , Description TEXT, Price TEXT , Url Text)");
        DB.execSQL("create table Comment (C_ID INTEGER primary key AUTOINCREMENT,prod_Name TEXT ,username TEXT, U_ID INTEGER , body TEXT )");
        DB.execSQL("create table feedback (orderID TEXT primary key AUTOINCREMENT,prod_Name TEXT ,username TEXT,  feedback TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists ProductDetails");
        DB.execSQL("drop table if exists Comment");
        DB.execSQL("drop table if exists feedback");
        onCreate(DB);
    }
    public boolean insert_product_data(String ID , String Category , String name ,  String Amount ,String Description,String Price , String url)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("ID",ID);
        contentValues.put("Category",Category);
        contentValues.put("Name",name);
        contentValues.put("Amount",Amount);
        contentValues.put("Description",Description);
        contentValues.put("Price",Price);
        contentValues.put("Url",url);
        long result =DB.insert("ProductDetails" ,null, contentValues);
        return result != -1;

    }

    public long insert_comment( String prod_Name , String username ,
                                  int U_ID ,String body)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("prod_Name",prod_Name);
        contentValues.put("username",username);
        contentValues.put("U_ID",U_ID);
        contentValues.put("body",body);
        long result =DB.insert("Comment" ,null, contentValues);
        return result ;

    }
    public Cursor getdata_comment()
    {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor =  DB.rawQuery("Select * from Comment " , null);
        return cursor;
    }

    public boolean update_product_data(String ID , String Category , String name , String Amount ,String Description,String Price  , String url)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
//        contentValues.put("ID",ID);
        contentValues.put("Category",Category);
        contentValues.put("Name",name);
        contentValues.put("Amount",Amount);
        contentValues.put("Price",Price);
        contentValues.put("Description",Description);
        Cursor cursor = DB.rawQuery("Select * from ProductDetails where ID = ?" , new String[] {ID});
        if(cursor.getCount()>0)
        {



            long result =DB.update("ProductDetails", contentValues,"ID=?",new String[] {ID});
            if (result==-1){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }



    public boolean delete_product_data(String ID )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        //ContentValues contentValues =new ContentValues();
        //  contentValues.put("ID",ID);
        // contentValues.put("Category",Category);
        //  contentValues.put("Amount",Amount);
        //contentValues.put("Descriebtion",Descriebtion);
        Cursor cursor = DB.rawQuery("Select * from ProductDetails where ID = ?" , new String[] {ID});
        if(cursor.getCount()>0)
        {



            long result =DB.delete("ProductDetails","ID=?",new String[] {ID});
            if (result==-1){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor =  DB.rawQuery("Select * from ProductDetails" , null);
        return cursor;
    }

    public Cursor getprodustsDetailsforSearch(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor =  DB.rawQuery("Select Name ,Description ,Price,Url from ProductDetails where Name = ?" , new String[] {name});
        return cursor;
    }
    public Cursor getid_amount(String desc , String name ,String price)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor =  DB.rawQuery("Select ID  ,Amount  from ProductDetails where   Description = ? and Name = ? and Price = ?  " , new String[]{desc , name , price});
        return cursor;
    }
    public Cursor getItemsByCategory(String categoryType) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select Name ,Description ,Price,Url from ProductDetails where Category = ?" , new String[] {categoryType});


        return cursor;

    }

    public Cursor getComments(String args){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c=db.rawQuery("Select username , body from Comment WHERE prod_Name =?",new String[] {args});

        return c;
    }
    public Cursor getfeed_back(){
        SQLiteDatabase db = getReadableDatabase();
        String[] attributes={"orderID","prod_Name","username","feedback"};
        Cursor c=db.query("feedback",attributes,null,null,null,null,null);
        if(c!=null)
            c.moveToFirst();
        db.close();
        return c;
    }


}

