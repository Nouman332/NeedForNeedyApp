package com.example.needforneedy;

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

import com.example.needforneedy.UserActivity;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2500;
    Animation animtop, animbottom;
    ImageView img;
    TextView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

       setContentView(R.layout.activity_main);

        //animations
        animtop = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        animbottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img = findViewById(R.id.imageView);
        logo = findViewById(R.id.textlogo);

        img.setAnimation(animtop);
        logo.setAnimation(animbottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
               startActivity(intent);

                finish();

            }
        },SPLASH_SCREEN);
    }
}