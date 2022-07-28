package com.example.needforneedy;


import static com.example.needforneedy.Donatefood.KEY_foodDrop;
import static com.example.needforneedy.ScholarShipdb.KEY_FullName;
import static com.example.needforneedy.ScholarShipdb.KEY_FatherName;
import static com.example.needforneedy.ScholarShipdb.KEY_CNICNumber;
import static com.example.needforneedy.ScholarShipdb.KEY_Email;
import static com.example.needforneedy.ScholarShipdb.DATABASE;
import static com.example.needforneedy.ScholarShipdb.KEY_Address;
import static com.example.needforneedy.ScholarShipdb.KEY_PercentageMatric;
import static com.example.needforneedy.ScholarShipdb.KEY_PhoneNo;
import static com.example.needforneedy.ScholarShipdb.KEY_Gender;
import static com.example.needforneedy.ScholarShipdb.KEY_PercentageFSC;
import static com.example.needforneedy.ScholarShipdb.KEY_PercentageFSC;
import static com.example.needforneedy.ScholarShipdb.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ScholarshipForm extends AppCompatActivity {

    TextInputLayout etFullName, etFatherName,etCNIC,etPhoneNo,etEmail,etHomeAddress,etMatricMarks,etFSC;
    Button btnSubmit;
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale, radioOther;

   ScholarShipdb db;
    SQLiteDatabase sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship_form);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);
        etFullName = findViewById(R.id.etFullName);
        etFatherName = findViewById(R.id.etFatherName);
        etCNIC = findViewById(R.id.etCNIC);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etEmail = findViewById(R.id.etEmail);
        etHomeAddress = findViewById(R.id.etHomeAddress);
        etMatricMarks = findViewById(R.id.etMatricMarks);
        etFSC = findViewById(R.id.etFSC);
        btnSubmit = findViewById(R.id.btnSubmit);
        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);

      insertData();

    }

    private void insertData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validation();
                String EmailTXT = etEmail.getEditText().getText().toString().trim();

                db = new ScholarShipdb(ScholarshipForm.this);

                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_FullName, etFullName.getEditText().getText().toString().trim());
                cv.put(KEY_FatherName, etFatherName.getEditText().getText().toString().trim());
                cv.put(KEY_CNICNumber, etCNIC.getEditText().getText().toString().trim());
                cv.put(KEY_PhoneNo, etPhoneNo.getEditText().getText().toString().trim());
                cv.put(KEY_Email, etEmail.getEditText().getText().toString());
                cv.put(KEY_Address, etHomeAddress.getEditText().getText().toString().trim());
                cv.put(KEY_PercentageMatric, etMatricMarks.getEditText().getText().toString());
                cv.put(KEY_PercentageFSC, etFSC.getEditText().getText().toString().trim());
                if(radioMale.isChecked()) {
                    cv.put(KEY_Gender,radioMale.getText().toString().trim());
                }
                if(radioFemale.isChecked()) {
                    cv.put(KEY_Gender,radioFemale.getText().toString().trim());
                }if(radioOther.isChecked()) {
                    cv.put(KEY_Gender,radioOther.getText().toString().trim());
                }


                Boolean checkuser = db.Checkusername(EmailTXT);
                if (checkuser == false) {

                    long result = sq.insert(TABLE_Name, null, cv);
                    if (result == -1) {

                        Toast.makeText(ScholarshipForm.this, "  Data not Inserted", Toast.LENGTH_SHORT).show();


                    } else {
                        etFullName.getEditText().setText("");
                        etFatherName.getEditText().setText("");
                        etCNIC.getEditText().setText("");
                        etPhoneNo.getEditText().setText("");
                        etEmail.getEditText().setText("");
                        etHomeAddress.getEditText().setText("");
                        etMatricMarks.getEditText().setText("");
                        etFSC.getEditText().setText("");
                        Toast.makeText(ScholarshipForm.this, "    Data Inserted", Toast.LENGTH_SHORT).show();


                    }
                } else {
                    Toast.makeText(ScholarshipForm.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void validation() {

        String FullName = etFullName.getEditText().getText().toString();
        String FatherName = etFatherName.getEditText().getText().toString();
        String CNIC = etCNIC.getEditText().getText().toString();
        String PhoneNo = etPhoneNo.getEditText().getText().toString();
        String Email = etEmail.getEditText().getText().toString();
        String HomeAddress = etHomeAddress.getEditText().getText().toString();
        String MatricMarks = etMatricMarks.getEditText().getText().toString();
        String FSC = etFSC.getEditText().getText().toString();

        if(!TextUtils.isEmpty(FullName) && !TextUtils.isEmpty(FatherName) && !TextUtils.isEmpty(CNIC)
                && !TextUtils.isEmpty(PhoneNo) && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(HomeAddress)
                && !TextUtils.isEmpty(MatricMarks) && !TextUtils.isEmpty(FSC)
        )
        {
            insertData();

        }
        else
        {
            Toast.makeText(ScholarshipForm.this,"Enter All Fields",Toast.LENGTH_SHORT).show();
        }
    }




}