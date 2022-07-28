package com.example.mainsemester;

import static com.example.mainsemester.Donatefood.KEY_FULL_ADDRESS;
//import static com.example.mainsemester.Donatefood.KEY_ID;
import static com.example.mainsemester.Donatefood.KEY_MOBILE_NUMBER;
import static com.example.mainsemester.Donatefood.KEY_Name;
import static com.example.mainsemester.Donatefood.KEY_Person;
import static com.example.mainsemester.Donatefood.KEY_foodDrop;
import static com.example.mainsemester.Donatefood.KEY_foodType;
import static com.example.mainsemester.Donatefood.TABLE_Name;

import android.annotation.SuppressLint;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class
DonateFoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    RadioGroup radioGroup;
    RadioButton radioDropFood,radioPickUp;

    TextInputLayout Name, MOBILE_NUMBER,FULL_ADDRESS,typeOfFood,ID;
    Button btnLocation,btnSubmit,btnclear;
    Donatefood db;
    SQLiteDatabase sq;

    String[] Spinner = {"1person","2person","3person","4person","5person","10person","15person","20person","100+ person"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);
        setContentView(R.layout.activity_donate_food);


        findid();
        insertData();




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });


    }


    private void findid() {



        radioGroup = findViewById(R.id.RadioGroup);
        radioDropFood = (RadioButton) findViewById(R.id.radioDropFood);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        radioPickUp = (RadioButton) findViewById(R.id.radioPickUp);
        Name = (TextInputLayout) findViewById(R.id.Name);
       //ID = (TextInputLayout) findViewById(R.id.ID);
        MOBILE_NUMBER = (TextInputLayout) findViewById(R.id.MOBILE_NUMBER);
        FULL_ADDRESS = (TextInputLayout) findViewById(R.id.FULL_ADDRESS);
        typeOfFood = (TextInputLayout) findViewById(R.id.typeOfFood);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnclear = findViewById(R.id.btnClear);
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name.getEditText().setText("");
                MOBILE_NUMBER.getEditText().setText("");
                FULL_ADDRESS.getEditText().setText("");
                typeOfFood.getEditText().setText("");



            }
        });
      btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getApplicationContext(),MapActivity.class));


            }
        });

    }


    private void insertData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validation();

                db = new Donatefood(DonateFoodActivity.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_Name, Name.getEditText().getText().toString().trim());
                cv.put(KEY_MOBILE_NUMBER, MOBILE_NUMBER.getEditText().getText().toString().trim());
                cv.put(KEY_FULL_ADDRESS, FULL_ADDRESS.getEditText().getText().toString().trim());
                cv.put(KEY_foodType, typeOfFood.getEditText().getText().toString().trim());
            //  cv.put(KEY_ID, ID.getEditText().getText().toString().trim());

                if(radioDropFood.isChecked()) {
                    cv.put(KEY_foodDrop,radioDropFood.getText().toString().trim());
                }
                if(radioPickUp.isChecked()) {
                    cv.put(KEY_foodDrop,radioPickUp.getText().toString().trim());
                }
                //for array
          cv.put(KEY_Person,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());

                long result = sq.insert(TABLE_Name, null, cv);
                if (result == -1) {
                    Toast.makeText(DonateFoodActivity.this, "  Data  not  Inserted", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(DonateFoodActivity.this, "    Data  Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),fetchdata.class));
                }

            }
        });

    }
    private void validation() {
        String uname = Name.getEditText().getText().toString().trim();
        String mobile = MOBILE_NUMBER.getEditText().getText().toString().trim();
        String address = FULL_ADDRESS.getEditText().getText().toString().trim();
        String foodType = typeOfFood.getEditText().getText().toString().trim();
        String rdDropFood = radioDropFood.getText().toString();
        String rdPickUp = radioPickUp.getText().toString();
        if(!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(foodType)) {
      insertData();
        }
        else
        {
            Toast.makeText(DonateFoodActivity.this,"Enter All Fields",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {





    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}