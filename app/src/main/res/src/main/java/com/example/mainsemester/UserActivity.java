package com.example.mainsemester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    Button bUser, bAdmin, bVolunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bUser = findViewById(R.id.user);
        bAdmin = findViewById(R.id.admin);
        bVolunteer = findViewById(R.id.volunteer);
        bVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, VolunteerActivity.class);
               startActivity(intent4);


            }
        });
    }

    public void onUserButtonClicked(View view) {
        Intent intent1 = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(intent1);
    }


    public void onAdminButtonClicked(View view) {
        Intent intent3 = new Intent(UserActivity.this, AdminLoginActivity.class);
        startActivity(intent3);
    }
}