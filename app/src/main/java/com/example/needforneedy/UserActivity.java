package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.needforneedy.VolunteerActivity;

public class UserActivity extends AppCompatActivity {

   Button userBox, volunteerBox, adminBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

       getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);


        userBox = findViewById(R.id.userBox);
        volunteerBox = findViewById(R.id.volunteerBox);
        adminBox = findViewById(R.id.adminBox);

        volunteerBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, VolunteerActivity.class);
               startActivity(intent4);
            }
        });
        userBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, HomeActivity.class);
                startActivity(intent4);
            }
        });
        adminBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, AdminLoginActivity.class);
                startActivity(intent4);
            }
        });

    }
}