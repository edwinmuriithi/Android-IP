package com.example.androidip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.androidip.databinding.ActivityMainBinding;
import com.example.androidip.databinding.ActivitySearchBinding;

public class MainActivity extends AppCompatActivity {

    //Binding Class
     private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get layout
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
//        setContentView(R.layout.activity_main);



    }
}