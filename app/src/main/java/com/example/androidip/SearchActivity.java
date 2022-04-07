package com.example.androidip;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivitySearchBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private TextView greetings;
    private static final String TAG = "SearchActivity";

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

                Methods method = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = method.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.e(TAG, "onResponse: code : "+ response.code() );

                        ArrayList<Model.data> data = response.body().getData();
                        
                        for (Model.data data1 : data){
                            Log.e(TAG, "onResponse: emails :" + data1.getEmail() );
                        }

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());

                    }
                });

                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //DropDown
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("Head", "Neck", "Chest", "Leg", "Back");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });


    }
}
