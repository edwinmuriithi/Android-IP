package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    private TextView greetings;

    //Binding Class
    private ActivitySearchBinding activitySearchBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get layout
        activitySearchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = activitySearchBinding.getRoot();
        setContentView(view);

        //Create get intent
        Intent i = getIntent();

//        receive value by getStringExtra
//        Key same as the one sent in login activity
        String string = i.getStringExtra("username");

        //display string in text view
        activitySearchBinding.dataPass.setText("Hi!! "+ string);


        //Search button Toast
        activitySearchBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchActivity.this, "Searching...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
