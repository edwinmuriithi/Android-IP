package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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


        //Registration Toast
        activitySignupBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                //Go to next layout
                Intent intent = new Intent(SignupActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

}
