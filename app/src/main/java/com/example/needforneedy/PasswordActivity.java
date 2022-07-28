package com.example.needforneedy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class PasswordActivity extends AppCompatActivity {

    TextInputLayout username;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

        username = findViewById(R.id.username);
        btnReset = findViewById(R.id.btnReset);

//        // calling the action bar
//        ActionBar actionBar = getSupportActionBar();
//        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}