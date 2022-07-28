package com.example.mainsemester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    Button SignUp, Login;
    TextInputLayout username, password;
    SQLiteDatabase sqLiteDatabase;
    userdatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        SignUp = (Button) findViewById(R.id.btnSignUp);
        Login = (Button) findViewById(R.id.btnGo);
        username = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);
        db = new userdatabase(LoginActivity.this);





        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getEditText().getText().toString().trim();
                String pass = password.getEditText().getText().toString().trim();
                // if(TextUtils.isEmpty(uname) && TextUtils.isEmpty(pass))
                if (uname.length() > 0 && pass.length() > 0) {
                    if (pass.length() < 8) {
                        Toast.makeText(LoginActivity.this, "Password must be minimum 8 characters", Toast.LENGTH_SHORT).show();
                    } else {
                        String lastname = username.getEditText().getText().toString();
                        String PasswordTXT = password.getEditText().getText().toString();
                        if (lastname.equals("") || PasswordTXT.equals(""))
                            Toast.makeText(LoginActivity.this, "Please Enter all the fields", Toast.LENGTH_SHORT).show();
                        else {
                            Boolean checkuserpass = db.checkuser_password(lastname, PasswordTXT);
                            if (checkuserpass == true) {

                                Toast.makeText(LoginActivity.this, "login Sucessfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                }
                else
                    {
                        Toast.makeText(LoginActivity.this, "Enter Both Fields", Toast.LENGTH_SHORT).show();
                    }
                }


        });
    }

}