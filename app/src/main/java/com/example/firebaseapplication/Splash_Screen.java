package com.example.firebaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {


    TextView appName;
    ImageView appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        appName = findViewById(R.id.appname);
        appLogo = findViewById(R.id.logoimage);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);
        appName.startAnimation(animation);

        //to close splash screen

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this,Login_Page.class);
                startActivity(intent);
                finish();
            }
        },2000);



    }
}