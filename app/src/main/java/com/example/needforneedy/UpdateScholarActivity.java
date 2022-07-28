package com.example.needforneedy;

import static com.example.needforneedy.ScholarShipdb.KEY_Address;
import static com.example.needforneedy.ScholarShipdb.KEY_CNICNumber;
import static com.example.needforneedy.ScholarShipdb.KEY_Email;
import static com.example.needforneedy.ScholarShipdb.KEY_FatherName;
import static com.example.needforneedy.ScholarShipdb.KEY_FullName;
import static com.example.needforneedy.ScholarShipdb.KEY_Gender;
import static com.example.needforneedy.ScholarShipdb.KEY_PercentageFSC;
import static com.example.needforneedy.ScholarShipdb.KEY_PercentageMatric;
import static com.example.needforneedy.ScholarShipdb.KEY_PhoneNo;
import static com.example.needforneedy.ScholarShipdb.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateScholarActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale, radioOther;
    TextInputLayout etFullName, etFatherName,etCNIC,etPhoneNo,etEmail,etHomeAddress,etMatricMarks,etFSC;
    ScholarShipdb db;
    SQLiteDatabase sq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_scholar);

       ScholarShipModel s = (ScholarShipModel) getIntent().getExtras().getSerializable("ScholarShip");
        etFullName = findViewById(R.id.etFullName);
        etFatherName = findViewById(R.id.etFatherName);
        etCNIC = findViewById(R.id.etCNIC);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etEmail = findViewById(R.id.etEmail);
        etHomeAddress = findViewById(R.id.etHomeAddress);
        etMatricMarks = findViewById(R.id.etMatricMarks);
        etFSC = findViewById(R.id.etFSC);

        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);

       etFullName.getEditText().setText(s.getEtFullName());
       etFatherName.getEditText().setText(s.getEtFatherName());
       etCNIC.getEditText().setText(s.getEtCNIC());
       etPhoneNo.getEditText().setText(s.getEtPhoneNo());
        etEmail.getEditText().setText(s.getEtEmail());
        etHomeAddress.getEditText().setText(s.getEtHomeAddress());
        etMatricMarks.getEditText().setText(s.getEtMatricMarks());
        etFSC.getEditText().setText(s.getEtFSC());


    }
    public  void update(View view){
        String EmailTXT = etEmail.getEditText().getText().toString().trim();
        ScholarShipModel s = (ScholarShipModel) getIntent().getExtras().getSerializable("ScholarShip");
        db = new ScholarShipdb(UpdateScholarActivity.this);

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

            long result = sq.update(Requestdata.TABLE_Name,cv,"FullName =?",new String[]{String.valueOf(s.getEtFullName())});
            if (result == -1) {

                Toast.makeText(UpdateScholarActivity.this, "  Data not Updated!", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(UpdateScholarActivity.this, "    Data Updated!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateScholarActivity.this,Admin.class);
                startActivity(intent);

            }
        } else {
            Toast.makeText(UpdateScholarActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
        }
    }

}
