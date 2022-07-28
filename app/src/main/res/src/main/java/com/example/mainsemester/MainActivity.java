package com.example.mainsemester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation animtop, animbottom;
    ImageView img;
    TextView logo, slogan;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //animations
        animtop = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        animbottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img = findViewById(R.id.imageView);
        logo = findViewById(R.id.textlogo);
        slogan = findViewById(R.id.textslogan);

        img.setAnimation(animtop);
        logo.setAnimation(animbottom);
        slogan.setAnimation(animbottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
               startActivity(intent);

                finish();

            }
        },SPLASH_SCREEN);
    }
}