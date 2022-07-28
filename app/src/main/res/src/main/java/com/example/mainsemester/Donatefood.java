package com.example.mainsemester;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Donatefood extends SQLiteOpenHelper {

    public static final String DATABASE = "donor_detail.db";
    public static final String KEY_Name = "Name";
    public static final String KEY_MOBILE_NUMBER = "MOBILE_NUMBER";
    public static final String TABLE_Name = "Details_user";
    public static final String KEY_FULL_ADDRESS = "FULL_ADDRESS";
    public static final String KEY_foodType = "foodType";
    public static  final String  KEY_foodDrop ="foodDrop";
    public static final String KEY_Person = "Person";
    public static final String KEY_ID = "ID";



    public Donatefood(@Nullable Context context) {
        super(context,DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

           String qry = "create table " + TABLE_Name + "('" + KEY_ID + "' PRIMARY KEY,'" + KEY_Name + "' TEXT ,'" + KEY_MOBILE_NUMBER + "'TEXT,'" + KEY_FULL_ADDRESS + "' TEXT ,'" + KEY_foodType + "'TEXT,'" + KEY_foodDrop + "'TEXT,'" + KEY_Person + "'TEXT)";
            db.execSQL(qry);
        } catch (Exception e) {
            Log.e("Donatefood", "error when created table");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry = "drop table if exists " + TABLE_Name;
       db.execSQL(qry);
        onCreate(db);

    }

    public Cursor readalldata() {
     SQLiteDatabase db = this.getWritableDatabase();
       String qry = "select * from "+ TABLE_Name;
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;

    }

}
