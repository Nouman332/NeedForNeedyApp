package com.example.mainsemester;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class userdatabase extends SQLiteOpenHelper {
    public static  final String  DATABASE ="User5.db";
    public static final String KEY_firstName = "firstName";
    public static  final String  KEY_lastName ="lastName";
    public static final String TABLE_Name = "Details_user";
    public static final String KEY_Email = "Email";
    public static  final String  KEY_Contact ="Contact";
    public static  final String  KEY_Password ="Password";
    public static  final int VER = 1;

    public userdatabase(@Nullable Context context) {
        super(context, DATABASE, null, VER);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("create table "+ TABLE_Name + "('"+KEY_firstName+"' TEXT ,'" +KEY_lastName+"'TEXT,'"+KEY_Email+"' TEXT ,'"+KEY_Contact+"'TEXT,'"+KEY_Password+"'TEXT)");
        }catch (Exception e){
            Log.e("userdatabase","error when created table");
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_Name);
        onCreate(db);


    }

    public boolean checkuser_password(String Email ,String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select  * from  Details_user where  Email = ? and Password = ?",new String[]{Email,Password});
        if(cursor.getCount() >0)
            return true;
        else
            return  false;
    }

    public Boolean Checkusername(String Email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select  * from Details_user  where  Email = ?",new String[]{Email});
        if(cursor.getCount() >0)
            return true;
        else
            return  false;
    }

}
