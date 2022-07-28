package com.example.needforneedy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class volunteerdatabase extends SQLiteOpenHelper {

    public static  final String  DATABASE ="VolunteerData.db";
    public static final String KEY_firstName = "firstName";
    public static  final String  KEY_lastName ="lastName";
    public static final String TABLE_Name = "Details_user";
    public static final String KEY_Email = "Email";
    public static  final String  KEY_Contact ="Contact";
    public static  final String  KEY_Password ="Password";
    public static  final String  KEY_ConfirmPassword ="ConfirmPassword";
    public static final String KEY_weekday ="weekday";
    public static final String KEY_Country = "Country";
    public static final String KEY_ID = "ID";
    public static  final int VER = 1;

    public volunteerdatabase(@Nullable Context context) {
        super(context, DATABASE, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL("create table "+ TABLE_Name + "('" + KEY_ID + "' PRIMARY KEY,'"+KEY_firstName+"' TEXT ,'" +KEY_lastName+"'TEXT,'"+KEY_Email+"' TEXT ,'"+KEY_Contact+"'TEXT,'"+KEY_Password+"'TEXT,'"+KEY_ConfirmPassword+"'TEXT,'"+KEY_weekday+"'TEXT,'"+KEY_Country+"'TEXT)");
        }catch (Exception e){
            Log.e("volunteerdatabase","error when created table");
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_Name);
        onCreate(db);

    }
    public boolean checkuser_password(String Email, String Password) {
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
public int updatepass(String email, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("Password", pass);
    return db.update("Details_user",contentValues,"Email = ?",new String[]{email});


}
    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from " + TABLE_Name + " order by id desc ";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;

    }

    public int deleteModel(String firstName) {
        SQLiteDatabase db = getWritableDatabase();

        return  db.delete(TABLE_Name,"firstName =?",new String[]{String.valueOf(firstName)});
    }
}
