package com.example.needforneedy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class NGOdatabase extends SQLiteOpenHelper {

    public static  final String  DATABASE ="NGOReg.db";
    public static final String KEY_NGOName = "NGOName";
    public static  final String  KEY_Website ="Website";
    public static final String TABLE_Name = "Details_user";
    public static final String KEY_Email = "Email";
    public static  final String  KEY_Phone ="Phone";
    public static  final String  KEY_Address ="Address";
    public static final String KEY_ID = "ID";
    public static  final int VER = 1;

    public NGOdatabase(@Nullable Context context) {
        super(context, DATABASE, null,VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL("create table "+ TABLE_Name + "('" + KEY_ID + "' PRIMARY KEY,'"+KEY_NGOName+"' TEXT ,'" +KEY_Website+"'TEXT,'"+KEY_Email+"' TEXT ,'"+KEY_Phone+"'TEXT,'"+KEY_Address+"'TEXT)");
        }catch (Exception e){
            Log.e("NGO_Reg","error when created table");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_Name);
        onCreate(db);
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

    } public Boolean Checkname(String NGOName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select  * from Details_user  where  NGOName = ?",new String[]{NGOName});
        if(cursor.getCount() >0)
            return true;
        else
            return  false;
    }

    public int deleteModel(String ngo_name) {

        SQLiteDatabase db = getWritableDatabase();

        return  db.delete(TABLE_Name,"NGOName =?",new String[]{String.valueOf(ngo_name)});
    }
}
