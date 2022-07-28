package com.example.needforneedy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class VolunteerView extends AppCompatActivity {



    TextView tv1, tv2,logOut;
   // TabItem tabViewDonations, tabViewRequest;
   // PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteerview);

        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);


        tv1 = findViewById(R.id.txtHeading1);
        tv2 = findViewById(R.id.txtHeading);
        logOut = findViewById(R.id.txtLogOut);
        SharedPreferences settings = getSharedPreferences(VolunteerActivity.LOGIN, 0);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String uname = extras.getString("USER_NAME");
            tv2.setText(uname);
        }
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(VolunteerView.this, VolunteerActivity.class);
                startActivity(intent);
            }
        });

    }
}