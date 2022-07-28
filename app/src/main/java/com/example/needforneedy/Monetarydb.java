package com.example.needforneedy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Monetarydb extends SQLiteOpenHelper {
    public static  final String  DATABASE ="Monetary_Donate.db";
    public static final String KEY_DonateAmount = "DonateAmount";
    public static  final String  KEY_FullName="FullName";
    public static  final String  KEY_ContactNo="ContactNo";
    public static final String TABLE_Name = "Details_user";
    public static final String KEY_ID = "ID";
    public static  final int VER = 1;

    public Monetarydb(@Nullable Context context) {
        super(context, DATABASE, null,VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("create table "+ TABLE_Name + "('" + KEY_ID + "' PRIMARY KEY,'"+KEY_DonateAmount+"' TEXT ,'" +KEY_FullName+"'TEXT,'"+KEY_ContactNo+"' TEXT )");
        }catch (Exception e){
            Log.e("NGO_Reg","error when created table");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from " + TABLE_Name + " order by id desc ";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;

    }

    public int deleteModel(String etName) {
        SQLiteDatabase db = getWritableDatabase();

        return  db.delete(TABLE_Name,"FullName =?",new String[]{String.valueOf(etName)});
    }
}
