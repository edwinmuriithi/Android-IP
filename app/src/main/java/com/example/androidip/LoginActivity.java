package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    //Binding Class
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        activityLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();

                //Go to next layout
                Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
