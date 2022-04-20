package com.example.androidip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.example.androidip.databinding.ActivityAnimationBinding;

public class Animation extends AppCompatActivity {

    private ActivityAnimationBinding activityAnimationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAnimationBinding = ActivityAnimationBinding.inflate(getLayoutInflater());
        View view = activityAnimationBinding.getRoot();
        setContentView(view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityAnimationBinding.lottie.animate().translationY(1500).setDuration(1000).setStartDelay(1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(Animation.this, SearchActivity.class));

            }
        },2000);
    }
}