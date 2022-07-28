package com.example.needforneedy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Admin extends AppCompatActivity {

    Button btnAdd,btnDonor,btnNeedy,btnVolunteer,btnMonetaryDonation,btnScholarShip,btnngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

        btnAdd = findViewById(R.id.btnAdd);
      btnDonor= findViewById(R.id.btnDonor);
      btnNeedy = findViewById(R.id.btnNeedy);
      btnMonetaryDonation = findViewById(R.id.btnMonetaryDonation);
        btnScholarShip = findViewById(R.id.btnScholarShip);
        btnVolunteer = findViewById(R.id.btnVolunteer);
        btnngo = findViewById(R.id.btnNGO);
        btnVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, Volunteerfetchdata.class);
                startActivity(i);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, NGO_Registration.class);
                startActivity(i);
            }
        });
        btnDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this,DonateFetchdta.class);
                startActivity(i);
            }
        });
        btnNeedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, requestfetchdata.class);
                startActivity(i);
            }
        });
        btnMonetaryDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, MonetaryDonationFetchdata.class);
                startActivity(i);
            }
        });
        btnScholarShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, ScholarShipFetchdata.class);
                startActivity(i);
            }
        });
        btnngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, NGOFetchdata.class);
                startActivity(i);
            }
        });
    }

}