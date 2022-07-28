package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class forget_password extends AppCompatActivity {

    TextInputLayout forget_email, Password, ConfirmPassword;
    Button btn_forget;
    volunteerdatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        forget_email = findViewById(R.id.forget_email);
        Password = (TextInputLayout) findViewById(R.id.password);
        ConfirmPassword = (TextInputLayout) findViewById(R.id.Confirmpassword);
        btn_forget = findViewById(R.id.btn_forget);
        db = new volunteerdatabase(forget_password.this);
        btn_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = forget_email.getEditText().getText().toString().trim();
                String pass = Password.getEditText().getText().toString().trim();
                String confirmpass = ConfirmPassword.getEditText().getText().toString().trim();

                if(email.equals("") || pass.equals("") || confirmpass.equals("")){
                    Toast.makeText(forget_password.this,"Enter All Fields",Toast.LENGTH_SHORT).show();

                }
else{
    if(pass.equals(confirmpass)){
        int updateclass = db.updatepass(email,pass);
        if(updateclass == 1){
            forget_email.getEditText().setText("");
            ConfirmPassword.getEditText().setText("");
            Toast.makeText(forget_password.this,"Successfully inserted!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(forget_password.this,VolunteerSignUp.class);
            startActivity(intent);
        }else{
            Toast.makeText(forget_password.this,"Invalid email!",Toast.LENGTH_SHORT).show();
        }
    }else{
        Toast.makeText(forget_password.this,"password mismatched!",Toast.LENGTH_SHORT).show();
    }
                }
            }

            });
        }
    }
