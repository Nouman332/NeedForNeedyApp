package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class
AdminLoginActivity extends AppCompatActivity {

    TextInputLayout username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

        login = (Button) findViewById(R.id.btnGo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }

    private void validateFields() {
        String uname = username.getEditText().getText().toString();
        String pass = password.getEditText().getText().toString();

        if(!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(pass))
        {
            if(uname.equals("admin") && pass.equals("xyz"))
            {
                Intent intent1 = new Intent(AdminLoginActivity.this, Admin.class);
                startActivity(intent1);
            }
            else
            {
                Toast.makeText(AdminLoginActivity.this,"Enter Correct Fields",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(AdminLoginActivity.this,"Enter Both Fields",Toast.LENGTH_SHORT).show();
        }
    }
}