package com.example.needforneedy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;

public class VolunteerActivity extends AppCompatActivity {

    Button signUpVolunteer, btnlogin;
    TextInputLayout username, password;
    volunteerdatabase db;
    String Token;
    public static final String LOGIN = "MyPrefsFile";

    //userV, passV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

        setContentView(R.layout.activity_volunteer);

        signUpVolunteer = (Button) findViewById(R.id.btnSignUp);
        btnlogin = (Button) findViewById(R.id.btnGo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        db = new volunteerdatabase(VolunteerActivity.this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                validateFields();


            }
        });


        signUpVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(VolunteerActivity.this, VolunteerSignUp.class);
                startActivity(intent2);
            }
        });

    }

    private void validateFields() {
        String lastname = username.getEditText().getText().toString();
        String PasswordTXT = password.getEditText().getText().toString();

        if(lastname.equals("") || PasswordTXT.equals("")) {
            Toast.makeText(VolunteerActivity.this, "Please Enter the Field", Toast.LENGTH_SHORT).show();
        }
            if (lastname.length() > 0 && PasswordTXT.length() > 0) {
                if (PasswordTXT.length() < 8) {
                    Toast.makeText(VolunteerActivity.this, "Password must be minimum 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = db.checkuser_password(lastname, PasswordTXT);
                    if (checkuserpass == true) {

                        Toast.makeText(VolunteerActivity.this, "login Sucessfully!", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(VolunteerActivity.this, VolunteerView.class);
                        intent1.putExtra("USER_NAME", lastname);
                        startActivity(intent1);

                    } else {
                        Toast.makeText(VolunteerActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        }

public void btn_forget(View view){
        Intent intent = new Intent(VolunteerActivity.this,forget_password.class);
        startActivity(intent);
}
}
