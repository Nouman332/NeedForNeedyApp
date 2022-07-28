package com.example.needforneedy;

import static com.example.needforneedy.NGOdatabase.KEY_Address;
import static com.example.needforneedy.Monetarydb.KEY_DonateAmount;
import static com.example.needforneedy.Monetarydb.KEY_FullName;
import static com.example.needforneedy.Monetarydb.KEY_ContactNo;
import static com.example.needforneedy.Monetarydb.DATABASE;
import static com.example.needforneedy.Monetarydb.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class monetaryDonationAct1 extends AppCompatActivity {

    EditText etAmount, etName, etNumber;
    Button btnDonate;
   Monetarydb db;
    SQLiteDatabase sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monetary_donation_act1);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);


        etAmount = findViewById(R.id.etAmount);
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        btnDonate = findViewById(R.id.btnDonate);

insertData();


    }
    private void insertData() {
        btnDonate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validateFields();
                db = new Monetarydb(monetaryDonationAct1.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_DonateAmount,etAmount.getText().toString().trim());
                cv.put(KEY_FullName,etName.getText().toString().trim());
                cv.put(KEY_ContactNo,etNumber.getText().toString().trim());
                long result = sq.insert(Monetarydb.TABLE_Name, null, cv);
                if (result == -1) {
                    Toast.makeText(monetaryDonationAct1.this, "  Data  not  Inserted", Toast.LENGTH_SHORT).show();


                } else {
                    etAmount.setText("");
                    etName.setText("");
                    etNumber.setText("");
                    Toast.makeText(monetaryDonationAct1.this, "    Data  Inserted", Toast.LENGTH_SHORT).show();

                }

            }


        });




    }

    private void validateFields() {

        String Amount = etAmount.getText().toString();
        String Name = etName.getText().toString();
        String Number = etNumber.getText().toString();

        if(!TextUtils.isEmpty(Amount) && !TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Number))
        {
            insertData();
                Intent intent1 = new Intent(monetaryDonationAct1.this, PaymentActivity.class);
                startActivity(intent1);

        }
        else
        {
            Toast.makeText(monetaryDonationAct1.this,"Enter All the Fields",Toast.LENGTH_SHORT).show();
        }

    }


}