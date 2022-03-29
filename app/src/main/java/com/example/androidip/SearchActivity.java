package com.example.androidip;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    //Binding Class
    private ActivitySearchBinding activitySearchBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get layout
        activitySearchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = activitySearchBinding.getRoot();
        setContentView(view);

        //Search button Toast
        activitySearchBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchActivity.this, "Searching...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
