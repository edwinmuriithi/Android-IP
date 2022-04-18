package com.example.androidip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidip.databinding.ActivityMainBinding;
import com.example.androidip.databinding.ActivitySearchBinding;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Binding Class
     private ActivityMainBinding activityMainBinding;
     RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get layout
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        //Onclick
        activityMainBinding.searchagain.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Go to next Layout
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
    });


        //recycler Constructor
        recyclerAdapter = new RecyclerAdapter();

        activityMainBinding.recyclerView.setAdapter(recyclerAdapter);
        initDiseaseName();//Calling the method

        //Check if User is logged in
       if (FirebaseAuth.getInstance().getCurrentUser() == null ){
           Intent intent = new Intent(this, LoginActivity.class);
           startActivity(intent);
           finish();
       }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initDiseaseName(){
        List<String> diseaseName = new ArrayList<>();
        diseaseName.add("Fever");
        diseaseName.add("Flu");
        diseaseName.add("Shaking");
        diseaseName.add("Headache");
        diseaseName.add("Tiredness");
        recyclerAdapter.updateDiseaseName(diseaseName);
    }

}