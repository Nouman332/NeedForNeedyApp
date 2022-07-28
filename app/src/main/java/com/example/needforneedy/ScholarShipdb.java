package com.example.needforneedy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ScholarShipdb extends SQLiteOpenHelper {

    public static final String DATABASE = "ScholarShip";
    public static final String KEY_FullName = "FullName";
    public static final String KEY_FatherName = "FatherName";
    public static final String TABLE_Name = "Details_user";
    public static final String KEY_CNICNumber = "CNICNumber";
    public static final String KEY_PhoneNo = "PhoneNo";
    public static  final String  KEY_Email ="Email";
    public static final String KEY_Address = "Address";
    public static final String KEY_Gender = "Gender";
    public static final String KEY_PercentageMatric = "PercentageMatric";
    public static final String KEY_PercentageFSC = "PercentageFSC";
    public static final String KEY_ID = "ID";
    public static  final int VER = 1;

    public ScholarShipdb(@Nullable Context context) {
        super(context, DATABASE, null,VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("create table "+ TABLE_Name + "('" + KEY_ID + "' PRIMARY KEY,'"+KEY_FullName+"' TEXT ,'" +KEY_FatherName+"'TEXT,'"+KEY_CNICNumber+"' TEXT ,'"+KEY_PhoneNo+"'TEXT,'"+KEY_Email+"'TEXT,'"+KEY_Address+"'TEXT,'"+KEY_Gender+"'TEXT,'"+KEY_PercentageMatric+"'TEXT,'"+KEY_PercentageFSC+"'TEXT)");
        }catch (Exception e){
            Log.e("Scholarship_Reg","error when created table");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Boolean Checkusername(String Email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select  * from Details_user  where  Email = ?",new String[]{Email});
        if(cursor.getCount() >0)
            return true;
        else
            return  false;
    }
    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from " + TABLE_Name + " order by id desc ";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;

    }
    public Boolean Checkname(String FullName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select  * from Details_user  where  FullName = ?",new String[]{FullName});
        if(cursor.getCount() >0)
            return true;
        else
            return  false;
    }

    public int deleteModel(String etFullName) {
        SQLiteDatabase db = getWritableDatabase();

        return  db.delete(TABLE_Name,"FullName =?",new String[]{String.valueOf(etFullName)});
    }
}
