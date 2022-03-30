package com.example.androidip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.window.SplashScreen;

import com.example.androidip.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    //Binding Class
    private ActivitySplashBinding activitySplashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = activitySplashBinding.getRoot();
        setContentView(view);
       //Splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Go to next layout
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();//End Splash Activity 
            }
        }, 2000);//Page delays for 2 seconds

    }
}