package com.example.mainsemester;

import static com.example.mainsemester.Requestdata.KEY_Country;
import static com.example.mainsemester.Requestdata.KEY_FULL_ADDRESS;
//import static com.example.mainsemester.Requestdata.KEY_ID;
import static com.example.mainsemester.Requestdata.KEY_MOBILE_NUMBER;
import static com.example.mainsemester.Requestdata.KEY_Name;
import static com.example.mainsemester.Requestdata.KEY_foodType;
import static com.example.mainsemester.Requestdata.TABLE_Name;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RequestFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    TextInputLayout Name, MOBILE_NUMBER, FULL_ADDRESS, foodType,ID;
    Button btnLocation, btnSubmit,btnClear;
    Requestdata db;
    SQLiteDatabase sq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_request_food);

        spinner = findViewById(R.id.spinner);
        Name = (TextInputLayout) findViewById(R.id.Name);
       //ID = (TextInputLayout) findViewById(R.id.ID);
        MOBILE_NUMBER = (TextInputLayout) findViewById(R.id.MOBILE_NUMBER);
        FULL_ADDRESS = (TextInputLayout) findViewById(R.id.FULL_ADDRESS);
        foodType = (TextInputLayout) findViewById(R.id.foodType);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getApplicationContext(),MapActivity.class));


            }
        });


        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnClear =findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Name.getEditText().setText("");
                MOBILE_NUMBER.getEditText().setText("");
              FULL_ADDRESS.getEditText().setText("");
               foodType.getEditText().setText("");



            }
        });
        insertData();
        spinner.setOnItemSelectedListener(this);

        String[] Spinner = {"Karachi","Lahore","Islamabad","Rawalpindi"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


    }



    private void insertData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validation();

                db = new Requestdata(RequestFood.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_Name, Name.getEditText().getText().toString().trim());
                cv.put(KEY_MOBILE_NUMBER, MOBILE_NUMBER.getEditText().getText().toString().trim());
                cv.put(KEY_FULL_ADDRESS, FULL_ADDRESS.getEditText().getText().toString().trim());
                cv.put(KEY_foodType, foodType.getEditText().getText().toString().trim());


                cv.put(KEY_Country,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());

                long result = sq.insert(TABLE_Name, null, cv);
                if (result == -1) {
                    Toast.makeText(RequestFood.this, "  Data  not  Inserted", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(RequestFood.this, "    Data  Inserted", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(RequestFood.this,requestfetchdata.class);
                        startActivity(intent);

                }
            }
        });

    }

    private void validation() {
            String uname = Name.getEditText().getText().toString().trim();
            String mobile = MOBILE_NUMBER.getEditText().getText().toString().trim();
            String address = FULL_ADDRESS.getEditText().getText().toString().trim();
            String food_Type = foodType.getEditText().getText().toString().trim();

            if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(food_Type)) {
               insertData();
                } else {
                Toast.makeText(RequestFood.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



