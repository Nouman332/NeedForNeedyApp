package com.example.needforneedy;

import static com.example.needforneedy.Donatefood.TABLE_Name;
import static com.example.needforneedy.Monetarydb.KEY_ContactNo;
import static com.example.needforneedy.Monetarydb.KEY_DonateAmount;
import static com.example.needforneedy.Monetarydb.KEY_FullName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMonetaryActivity extends AppCompatActivity {
    EditText etAmount, etName, etNumber;
    Monetarydb db;
    SQLiteDatabase sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_monetary);
       MonetaryModel s = (MonetaryModel) getIntent().getExtras().getSerializable("Monetary");
        etAmount = findViewById(R.id.etAmount);
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
       etAmount.setText(s.getEtAmount());
      etName.setText(s.getEtName());
      etNumber.setText(s.getEtNumber());

    }
    public  void update(View view){

        db = new Monetarydb(UpdateMonetaryActivity.this);
        MonetaryModel s = (MonetaryModel) getIntent().getExtras().getSerializable("Monetary");
        sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DonateAmount,etAmount.getText().toString().trim());
        cv.put(KEY_FullName,etName.getText().toString().trim());
        cv.put(KEY_ContactNo,etNumber.getText().toString().trim());
        long result = sq.update(TABLE_Name,cv,"FullName" +
                " =?",new String[]{String.valueOf(s.getEtName())});
        if (result == -1) {
            Toast.makeText(UpdateMonetaryActivity.this, "  Data  not  Updated!", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(UpdateMonetaryActivity.this, "    Data  Updated!", Toast.LENGTH_SHORT).show();
            Intent adminintent = new Intent(UpdateMonetaryActivity.this,Admin.class);
            startActivity(adminintent);
        }

    }
    }
