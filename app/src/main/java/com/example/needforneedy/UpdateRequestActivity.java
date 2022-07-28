package com.example.needforneedy;

import static com.example.needforneedy.Requestdata.KEY_FULL_ADDRESS;
import static com.example.needforneedy.Requestdata.KEY_MOBILE_NUMBER;
import static com.example.needforneedy.Requestdata.KEY_Name;
import static com.example.needforneedy.Requestdata.KEY_Country;
import static com.example.needforneedy.Donatefood.KEY_foodDrop;
import static com.example.needforneedy.Requestdata.KEY_foodType;
import static com.example.needforneedy.Requestdata.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    android.widget.Spinner spinner;
    TextInputLayout Name, MOBILE_NUMBER, FULL_ADDRESS, typeOfFood;
    SQLiteDatabase sq;
   Requestdata db;


    String[] Spinner = {"Karachi","Lahore","Islamabad","Rawalpindi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request);
    RequestModel s = (RequestModel) getIntent().getExtras().getSerializable("Request");


        Name = (TextInputLayout) findViewById(R.id.Name);

        spinner = findViewById(R.id.spinner);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(UpdateRequestActivity.this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        MOBILE_NUMBER = (TextInputLayout) findViewById(R.id.MOBILE_NUMBER);
        FULL_ADDRESS = (TextInputLayout) findViewById(R.id.FULL_ADDRESS);
        typeOfFood= (TextInputLayout) findViewById(R.id.typeOfFood);


        Name.getEditText().setText(s.getName());
        MOBILE_NUMBER.getEditText().setText(s.getMOBILE_NUMBER());
        FULL_ADDRESS.getEditText().setText(s.getFULL_ADDRESS());
        typeOfFood.getEditText().setText(s.getFoodType());
    }
    public  void update(View view){


      RequestModel s = (RequestModel) getIntent().getExtras().getSerializable("Request");
        db = new Requestdata(UpdateRequestActivity.this);
        sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_Name, Name.getEditText().getText().toString().trim());
        cv.put(KEY_MOBILE_NUMBER, MOBILE_NUMBER.getEditText().getText().toString().trim());
        cv.put(KEY_FULL_ADDRESS, FULL_ADDRESS.getEditText().getText().toString().trim());
        cv.put(KEY_foodType, typeOfFood.getEditText().getText().toString().trim());
        //  cv.put(KEY_ID, ID.getEditText().getText().toString().trim());


        cv.put(KEY_Country,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());


        long result = sq.update(TABLE_Name,cv,"Name =?",new String[]{String.valueOf(s.getName())});

        if(result == -1) {

            Toast.makeText(UpdateRequestActivity.this, "  Data not Updated!", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(UpdateRequestActivity.this, "    Data  Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateRequestActivity.this,Admin.class);
            startActivity(intent);

        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}