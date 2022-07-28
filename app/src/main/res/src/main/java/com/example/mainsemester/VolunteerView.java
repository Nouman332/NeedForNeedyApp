package com.example.mainsemester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class VolunteerView extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPage;

    TextView tv1, tv2;
   // TabItem tabViewDonations, tabViewRequest;
   // PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteerview);

       tv1 = findViewById(R.id.txtHeading1);
        tv2 = findViewById(R.id.txtHeading);
        tabLayout = findViewById(R.id.tablayout);

       viewPage = findViewById(R.id.viewPage);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String uname = extras.getString("USER_NAME");
            tv2.setText(uname);
        }

        tabLayout.addTab(tabLayout.newTab().setText("DONATIONS"));
        tabLayout.addTab(tabLayout.newTab().setText("REQUEST"));
        viewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                       DonorFragment b1 = new DonorFragment();
                        return b1;
                    case 1:
                        foodRequestFragment b2 = new foodRequestFragment();
                        return b2;
                    default:
                        return  null;
                }

            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               viewPage .setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}