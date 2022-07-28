package com.example.mainsemester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawerlayout, toolbar,R.string.open, R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
//                        Intent intentHome = new Intent(HomeActivity.this, HomeActivity.class);
//                        startActivity(intentHome);
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.Donatefood:
                        Intent intentDonate = new Intent(HomeActivity.this, DonateFoodActivity.class);
                        startActivity(intentDonate);
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.Requestfood:
                        Intent intentRequest = new Intent(HomeActivity.this, RequestFood.class);
                        startActivity(intentRequest);
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;

//                    case R.id.Volunteer:
//                        Intent intentVolun = new Intent(HomeActivity.this, VolunteerActivity.class);
//                        startActivity(intentVolun);
//                        drawerlayout.closeDrawer(GravityCompat.START);
//                        break;

                    case R.id.search:
                        Intent intentSearch = new Intent(HomeActivity.this, LocationActivity.class);
                        startActivity(intentSearch);
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.about:
                        Intent intentAbout = new Intent(HomeActivity.this, About.class);
                        startActivity(intentAbout);
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.share)
        {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }

        else if(item.getItemId() == R.id.lang)
        {
//            Intent intentAdmin = new Intent(HomeActivity.this, Language.class);
//            startActivity(intentAdmin);
            String arr[] = {"English", "Spanish", "French"};
            AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);

            alert.setTitle("Choose Language");
            alert.setSingleChoiceItems(arr, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == 0)
                    {
                        setLocale("en");
                        recreate();
                    }
                    else if(which == 1)
                    {
                        setLocale("es");
                        recreate();
                    }
                    else if(which == 2)
                    {
                        setLocale("fr");
                        recreate();
                    }
                    dialog.dismiss();
                }
            });
            alert.create();
            alert.show();
        }
        else if(item.getItemId() == R.id.logout)
        {
            Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLocale(String en)
    {
        Locale locale= new Locale(en);
        locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor share = getSharedPreferences("Settings", MODE_PRIVATE).edit();

        share.putString("MyLang", en);
    }
    void LoadLang()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String Lang = sharedPreferences.getString("MyLang", "");
        setLocale(Lang);
    }

    public void DonateCardClicked(View view) {
        Intent intentDonate = new Intent(HomeActivity.this, DonateFoodActivity.class);
        startActivity(intentDonate);
    }

    public void RequestCardClicked(View view) {
        Intent intentRequest = new Intent(HomeActivity.this, RequestFood.class);
        startActivity(intentRequest);
    }

    public void SearchCardClicked(View view) {
        Intent intentSearch = new Intent(HomeActivity.this, LocationActivity.class);
        startActivity(intentSearch);
    }

    public void AboutCardClicked(View view) {
        Intent intentAbout = new Intent(HomeActivity.this, About.class);
        startActivity(intentAbout);
    }
}