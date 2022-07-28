package com.example.needforneedy;

import static com.example.needforneedy.userdatabase.KEY_Contact;
import static com.example.needforneedy.userdatabase.KEY_Email;
import static com.example.needforneedy.userdatabase.KEY_Password;
import static com.example.needforneedy.userdatabase.KEY_firstName;
import static com.example.needforneedy.userdatabase.KEY_lastName;
import static com.example.needforneedy.volunteerdatabase.KEY_weekday;
import static com.example.needforneedy.userdatabase.TABLE_Name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

   TextInputLayout firstName, lastName,email,phone,Password;
    Button btnSignUp,btnSignIn,btnClear;
    userdatabase db;
    SQLiteDatabase sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

        setContentView(R.layout.activity_sign_up);

        //GER ALL ID's
        firstName = (TextInputLayout) findViewById(R.id.firstName);
        lastName = (TextInputLayout) findViewById(R.id.user);
        email = (TextInputLayout) findViewById(R.id.email);
        phone = (TextInputLayout) findViewById(R.id.phone);
        Password = (TextInputLayout) findViewById(R.id.userPass);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnClear = (Button) findViewById(R.id.btnClear);
        insertData();

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
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(SignUpActivity.this, LoginActivity.class);
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

                    db = new userdatabase(SignUpActivity.this);

                    sq = db.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(KEY_firstName,firstName.getEditText().getText().toString().trim());
                    cv.put(KEY_lastName,lastName.getEditText().getText().toString().trim());
                    cv.put(KEY_Email,email.getEditText().getText().toString().trim());
                    cv.put(KEY_Contact,phone.getEditText().getText().toString().trim());
                    cv.put(KEY_Password, Password.getEditText().getText().toString().trim());

                    Boolean checkuser = db.Checkusername(EmailTXT);
                    if (checkuser == false) {

                        long result = sq.insert(TABLE_Name, null, cv);
                        if(result == 1) {

                                Toast.makeText(SignUpActivity.this, "  Data not Inserted", Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(SignUpActivity.this, "    Data Inserted", Toast.LENGTH_SHORT).show();


                            }
                        }else{
                            Toast.makeText(SignUpActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }

                    }


            });




        }




    private void validation() {

        String fName = firstName.getEditText().getText().toString().trim();
        String uName = lastName.getEditText().getText().toString().trim();
        String e_mail = email.getEditText().getText().toString().trim();
        String phoneNo = phone.getEditText().getText().toString().trim();
        String pass = Password.getEditText().getText().toString().trim();

        if (!TextUtils.isEmpty(fName) && !TextUtils.isEmpty(uName) && !TextUtils.isEmpty(e_mail) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(pass)) {
            {
                insertData();

            }

        }
        else {
            Toast.makeText(SignUpActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }
}