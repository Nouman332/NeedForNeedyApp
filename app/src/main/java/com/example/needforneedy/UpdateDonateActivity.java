package com.example.needforneedy;

import static com.example.needforneedy.Donatefood.KEY_FULL_ADDRESS;
import static com.example.needforneedy.Donatefood.KEY_MOBILE_NUMBER;
import static com.example.needforneedy.Donatefood.KEY_Name;
import static com.example.needforneedy.Donatefood.KEY_Person;
import static com.example.needforneedy.Donatefood.KEY_foodDrop;
import static com.example.needforneedy.Donatefood.KEY_foodType;
import static com.example.needforneedy.Donatefood.TABLE_Name;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateDonateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    RadioGroup radioGroup;
    RadioButton radioDropFood, radioPickUp;
    Spinner spinner;
    TextInputLayout Name, MOBILE_NUMBER, FULL_ADDRESS, typeOfFood;
    SQLiteDatabase sq;
    Donatefood db;

    String[] Spinner = {"1person","2person","3person","4person","5person","10person","15person","20person","100+ person"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_donate);

        Donatemodel s = (Donatemodel) getIntent().getExtras().getSerializable("Donates");

        radioPickUp = (RadioButton) findViewById(R.id.radioPickUp);
        Name = (TextInputLayout) findViewById(R.id.Name);

        spinner = findViewById(R.id.spinner);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(UpdateDonateActivity.this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        MOBILE_NUMBER = (TextInputLayout) findViewById(R.id.MOBILE_NUMBER);
        FULL_ADDRESS = (TextInputLayout) findViewById(R.id.FULL_ADDRESS);
       typeOfFood= (TextInputLayout) findViewById(R.id.typeOfFood);
        radioGroup = findViewById(R.id.RadioGroup);
        radioDropFood = (RadioButton) findViewById(R.id.radioDropFood);

        Name.getEditText().setText(s.getName());
       MOBILE_NUMBER.getEditText().setText(s.getMOBILE_NUMBER());
        FULL_ADDRESS.getEditText().setText(s.getFULL_ADDRESS());
       typeOfFood.getEditText().setText(s.getTypeOfFood());
        if (radioDropFood.isChecked()) {
            radioDropFood.setText(s.getFoodDrop());
        }
        if (radioPickUp.isChecked()) {
            radioPickUp.setText(s.getFoodDrop());
        }


    }
    public  void update(View view){


        Donatemodel s = (Donatemodel) getIntent().getExtras().getSerializable("Donates");
        db = new Donatefood(UpdateDonateActivity.this);
        sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_Name, Name.getEditText().getText().toString().trim());
        cv.put(KEY_MOBILE_NUMBER, MOBILE_NUMBER.getEditText().getText().toString().trim());
        cv.put(KEY_FULL_ADDRESS, FULL_ADDRESS.getEditText().getText().toString().trim());
        cv.put(KEY_foodType, typeOfFood.getEditText().getText().toString().trim());
        //  cv.put(KEY_ID, ID.getEditText().getText().toString().trim());

        if (radioDropFood.isChecked()) {
            cv.put(KEY_foodDrop, radioDropFood.getText().toString().trim());
        }
        if (radioPickUp.isChecked()) {
            cv.put(KEY_foodDrop, radioPickUp.getText().toString().trim());
        }
        cv.put(KEY_Person,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());


            long result = sq.update(TABLE_Name,cv,"Name =?",new String[]{String.valueOf(s.getName())});

            if(result == -1) {

                Toast.makeText(UpdateDonateActivity.this, "  Data not Updated!", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(UpdateDonateActivity.this, "    Data  Updated", Toast.LENGTH_SHORT).show();

                Intent adminintent = new Intent(UpdateDonateActivity.this,Admin.class);
                startActivity(adminintent);

            }
        }











    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}