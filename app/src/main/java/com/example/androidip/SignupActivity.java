package com.example.androidip;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    //Binding Class
    private ActivitySignupBinding activitySignupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = activitySignupBinding.getRoot();
        setContentView(view);
    }
}
