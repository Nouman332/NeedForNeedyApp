package com.example.needforneedy;

import static com.example.needforneedy.NGOdatabase.KEY_Address;
import static com.example.needforneedy.NGOdatabase.KEY_Email;
import static com.example.needforneedy.NGOdatabase.KEY_NGOName;
import static com.example.needforneedy.NGOdatabase.KEY_Phone;
import static com.example.needforneedy.NGOdatabase.KEY_Website;
import static com.example.needforneedy.NGOdatabase.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateNGOActivity extends AppCompatActivity {
    TextInputLayout NGO_name,etWebsite,email,phone,etAddress;
    NGOdatabase db;
    SQLiteDatabase sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       NGOmodel s = (NGOmodel) getIntent().getExtras().getSerializable("NGO");

        setContentView(R.layout.activity_update_ngoactivity);
        NGO_name = findViewById(R.id.NGO_Name);
        etWebsite = findViewById(R.id.etWebsite);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        etAddress = findViewById(R.id.etAddress);
     NGO_name.getEditText().setText(s.getNGO_name());
      etWebsite.getEditText().setText(s.getEtWebsite());
       email.getEditText().setText(s.getEmail());
      phone.getEditText().setText(s.getPhone());
       etAddress.getEditText().setText(s.getEtAddress());

    }
    public  void update(View view) {

        String EmailTXT = email.getEditText().getText().toString().trim();

        db = new NGOdatabase(UpdateNGOActivity.this);
        NGOmodel s = (NGOmodel) getIntent().getExtras().getSerializable("NGO" +
                "");
        sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NGOName,NGO_name.getEditText().getText().toString().trim());
        cv.put(KEY_Website,etWebsite.getEditText().getText().toString().trim());
        cv.put(KEY_Email,email.getEditText().getText().toString().trim());
        cv.put(KEY_Phone,phone.getEditText().getText().toString().trim());
        cv.put(KEY_Address, etAddress.getEditText().getText().toString().trim());

        Boolean checkuser = db.Checkusername(EmailTXT);
        if (checkuser == false) {

            long result = sq.update(Requestdata.TABLE_Name,cv,"NGOName =?",new String[]{String.valueOf(s.getNGO_name())});
            if(result == -1) {

                Toast.makeText(UpdateNGOActivity.this, "  Data not Updated!", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(UpdateNGOActivity.this, "    Data Updated!", Toast.LENGTH_SHORT).show();
                Intent adminintent = new Intent(UpdateNGOActivity.this,Admin.class);
                startActivity(adminintent);

            }
        }else{
            Toast.makeText(UpdateNGOActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
        }

    }

}

