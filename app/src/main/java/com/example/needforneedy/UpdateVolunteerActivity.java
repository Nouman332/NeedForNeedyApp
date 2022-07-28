package com.example.needforneedy;

import static com.example.needforneedy.Requestdata.KEY_Country;
import static com.example.needforneedy.volunteerdatabase.KEY_ConfirmPassword;
import static com.example.needforneedy.volunteerdatabase.KEY_Contact;
import static com.example.needforneedy.volunteerdatabase.KEY_Email;
import static com.example.needforneedy.volunteerdatabase.KEY_Password;
import static com.example.needforneedy.volunteerdatabase.KEY_firstName;
import static com.example.needforneedy.volunteerdatabase.KEY_lastName;
import static com.example.needforneedy.volunteerdatabase.KEY_weekday;
import static com.example.needforneedy.volunteerdatabase.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateVolunteerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    android.widget.Spinner spinner;

    TextInputLayout firstName, lastName, email, phone, Password, ConfirmPassword;
    CheckBox chkMonday, chkTuesday, chkWednesday, chkThursday, chkFriday, chkSaturday, chkSunday;

    SQLiteDatabase sq;
    volunteerdatabase db;
    String[] Spinner = {"Karachi", "Lahore", "Islamabad", "Rawalpindi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_volunteer);
        VolunteerModel s = (VolunteerModel) getIntent().getExtras().getSerializable("Volunteer");
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        firstName = (TextInputLayout) findViewById(R.id.firstName);
        lastName = (TextInputLayout) findViewById(R.id.user);
        email = (TextInputLayout) findViewById(R.id.email);
        phone = (TextInputLayout) findViewById(R.id.phone);
        Password = (TextInputLayout) findViewById(R.id.password);
        ConfirmPassword = (TextInputLayout) findViewById(R.id.Confirmpassword);
        chkMonday = (CheckBox) findViewById(R.id.chkMonday);
        chkTuesday = (CheckBox) findViewById(R.id.chkTuesday);
        chkWednesday = (CheckBox) findViewById(R.id.chkWednesday);
        chkThursday = (CheckBox) findViewById(R.id.chkThursday);
        chkFriday = (CheckBox) findViewById(R.id.chkFriday);
        chkSaturday = (CheckBox) findViewById(R.id.chkSaturday);
        chkSunday = (CheckBox) findViewById(R.id.chkSunday);

        firstName.getEditText().setText(s.getFirstName());
        lastName.getEditText().setText(s.getLastName());
        email.getEditText().setText(s.getEmail());
        phone.getEditText().setText(s.getPhone());
        Password.getEditText().setText(s.getPassword());
        ConfirmPassword.getEditText().setText(s.getConfirmPassword());


    }

    public void update(View view) {

        VolunteerModel s = (VolunteerModel) getIntent().getExtras().getSerializable("Volunteer");
        String EmailTXT = email.getEditText().getText().toString().trim();
        String pass = Password.getEditText().getText().toString().trim();
        String confirmpass = ConfirmPassword.getEditText().getText().toString().trim();

        db = new volunteerdatabase(UpdateVolunteerActivity.this);

        sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_firstName, firstName.getEditText().getText().toString().trim());
        cv.put(KEY_lastName, lastName.getEditText().getText().toString().trim());
        cv.put(KEY_Email, email.getEditText().getText().toString().trim());
        cv.put(KEY_Contact, phone.getEditText().getText().toString().trim());
        cv.put(KEY_Password, Password.getEditText().getText().toString().trim());
        if (pass.equals(confirmpass)) {
            cv.put(KEY_ConfirmPassword, ConfirmPassword.getEditText().getText().toString().trim());
        } else {
            Toast.makeText(UpdateVolunteerActivity.this, "Password mismatched", Toast.LENGTH_SHORT).show();

        }


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

            long result = sq.update(Requestdata.TABLE_Name,cv,"firstName =?",new String[]{String.valueOf(s.getFirstName())});
            if (result == -1) {

                Toast.makeText(UpdateVolunteerActivity.this, "  Data not Updated!", Toast.LENGTH_SHORT).show();


            } else {

                Toast.makeText(UpdateVolunteerActivity.this, "    Data Updated!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateVolunteerActivity.this,Admin.class);
                startActivity(intent);

            }
        }
        else{
            Toast.makeText(UpdateVolunteerActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}