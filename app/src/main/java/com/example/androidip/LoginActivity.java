package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private EditText uname;
    private Button start;

    //Binding Class
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        activityLoginBinding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();


                //Go to next layout
                Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
//    public void sendData() {
//        //get the value inputted
//        String string = uname.getText().toString();
//
//        //Create Intent object of this class Context
//        Intent i = new Intent(getApplicationContext(), SearchActivity.class);
//
//        //putExtra puts value in key and username will receive and put the string
//        i.putExtra("username", string);
//        startActivity(i);
//
//    }

}
