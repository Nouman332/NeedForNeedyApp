package com.example.mainsemester;
import static com.example.mainsemester.Requestdata.KEY_Country;
import static com.example.mainsemester.volunteerdatabase.KEY_firstName;
import static com.example.mainsemester.volunteerdatabase.KEY_lastName;
import static com.example.mainsemester.volunteerdatabase.KEY_Email;
import static com.example.mainsemester.volunteerdatabase.KEY_Contact;
import static com.example.mainsemester.volunteerdatabase.KEY_Password;
import static com.example.mainsemester.volunteerdatabase.KEY_weekday;
import static com.example.mainsemester.volunteerdatabase.TABLE_Name;





import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class VolunteerSignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;

            TextInputLayout firstName,lastName, email, phone, Password;
    CheckBox chkMonday, chkTuesday, chkWednesday, chkThursday, chkFriday, chkSaturday, chkSunday;
    Button btnSignUp, btnSignIn,btnClear;
    SQLiteDatabase sq;
    volunteerdatabase db;
    String[] Spinner = {"Karachi","Lahore","Islamabad","Rawalpindi"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_volunteer_sign_up);

        //GET ALL IDs
        spinner = (Spinner) findViewById(R.id.spinner);
        firstName = (TextInputLayout) findViewById(R.id.firstName);
        lastName = (TextInputLayout) findViewById(R.id.user);
        email = (TextInputLayout) findViewById(R.id.email);
        phone = (TextInputLayout) findViewById(R.id.phone);
        Password = (TextInputLayout) findViewById(R.id.userPass);
        chkMonday = (CheckBox) findViewById(R.id.chkMonday);
        chkTuesday = (CheckBox) findViewById(R.id.chkTuesday);
        chkWednesday = (CheckBox) findViewById(R.id.chkWednesday);
        chkThursday = (CheckBox) findViewById(R.id.chkThursday);
        chkFriday = (CheckBox) findViewById(R.id.chkFriday);
        chkSaturday = (CheckBox) findViewById(R.id.chkSaturday);
        chkSunday = (CheckBox) findViewById(R.id.chkSunday);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.getEditText().setText("");
                lastName.getEditText().setText("");
                phone.getEditText().setText("");
                email.getEditText().setText("");
                Password.getEditText().setText("");


            }
        });
        insertData();


        //SPINNER ADAPTER FOR CITY NAMES

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(VolunteerSignUp.this, VolunteerActivity.class);
                startActivity(intent1);
            }
        });


    }

    private void insertData() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validation();
                String EmailTXT = email.getEditText().getText().toString().trim();
                db = new volunteerdatabase(VolunteerSignUp.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_firstName, firstName.getEditText().getText().toString().trim());
                cv.put(KEY_lastName, lastName.getEditText().getText().toString().trim());
                cv.put(KEY_Email, email.getEditText().getText().toString().trim());
                cv.put(KEY_Contact, phone.getEditText().getText().toString().trim());
                cv.put(KEY_Password, Password.getEditText().getText().toString().trim());
                cv.put(KEY_Country, spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());



                if (chkMonday.isChecked()) {

                     cv.put(KEY_weekday, chkMonday.getText().toString());
                }
                if (chkTuesday.isChecked()) {
                    cv.put(KEY_weekday, chkTuesday.getText().toString());
                }
                if (chkWednesday.isChecked()) {
                    cv.put(KEY_weekday, chkWednesday.getText().toString());
                }
                if (chkThursday.isChecked()) {
                    cv.put(KEY_weekday, chkThursday.getText().toString());
                }
                if (chkFriday.isChecked()) {
                    cv.put(KEY_weekday, chkFriday.getText().toString());
                }
                if (chkSaturday.isChecked()) {
                    cv.put(KEY_weekday, chkSaturday.getText().toString());
                }
                if (chkSunday.isChecked()) {
                    cv.put(KEY_weekday, chkSunday.getText().toString());
                }
                Boolean checkuser = db.Checkusername(EmailTXT);
                if (checkuser == false) {

                    long result = sq.insert(userdatabase.TABLE_Name, null, cv);
                    if (result == 1) {

                        Toast.makeText(VolunteerSignUp.this, "  Data not Inserted", Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(VolunteerSignUp.this, "    Data Inserted", Toast.LENGTH_SHORT).show();


                    }
                }
                      else{
                        Toast.makeText(VolunteerSignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }



        });


    }

    private void validation() {

        String fName = firstName.getEditText().getText().toString().trim();
        String lName = lastName.getEditText().getText().toString().trim();
        String phoneNo = phone.getEditText().getText().toString().trim();
        String pass = Password.getEditText().getText().toString().trim();

        if(!TextUtils.isEmpty(fName) && !TextUtils.isEmpty(lName) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(pass)) {
            insertData();

        }
        else
        {
            Toast.makeText(VolunteerSignUp.this,"Enter All Fields",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



