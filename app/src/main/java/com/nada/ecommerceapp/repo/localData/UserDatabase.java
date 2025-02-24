package com.nada.ecommerceapp.repo.localData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nada.ecommerceapp.repo.remoteData.UsersModel;
import com.nada.ecommerceapp.viewmodels.UserViewModel;

import java.util.List;

public class UserDatabase  extends SQLiteOpenHelper {
    public static final String TABLENAME="users";
    UserViewModel userViewModel ;
    public UserDatabase(@Nullable Context context) {
        super(context, "reg.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " +TABLENAME+ " (id integer   primary key AUTOINCREMENT, email Text ,username TEXT , password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists  " +TABLENAME);

    }

    public Boolean insertData (String username ,String email , String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("email",email);
        values.put("username",username);
        values.put("password", password);
        long result = db.insert(TABLENAME, null , values);
//        if (result !=-1)
//            RetrofitClient.getInstance().getMyApi().getUsers().;
        return result != -1;
    }

    public Cursor checkPasswordAndEmail(String email, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id from "+ TABLENAME +  " where  email=? and password=?"  , new String[]{email,password});


        return cursor;
    }
    public Cursor getUsername(int id)    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select username from "+ TABLENAME +  " where  id =?"  , new String[]{String.valueOf( id)});


        return cursor;
    }
    public boolean checkEmail(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLENAME +  " where  email=? "  , new String[]{email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }


    public void addUsersFromAPI(){
        List<UsersModel> usersModels = userViewModel.getUser().getValue() ;
        UsersModel usersModel ;
        for (int i = 0 ; i < usersModels.size() ; i++){
            usersModel = usersModels.get(i) ;
            insertData(usersModel.getName() , usersModel.getEmail() , usersModel.getPassword()) ;
        }
    }

    public void addUsers(){
        List<UsersModel> usersModels = userViewModel.getUser().getValue() ;
        UsersModel usersModel ;
        for (int i = 0 ; i < usersModels.size() ; i++){
            usersModel = usersModels.get(i) ;
            insertData(usersModel.getName() , usersModel.getEmail() , usersModel.getPassword()) ;
        }
    }
}
