package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivityLoginBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {


    String email,password;
    private Button start;
    private FirebaseAuth mAuth;

    //Binding Class
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        //Check if user is logged in
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        activityLoginBinding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the user input
                email = activityLoginBinding.email.getText().toString().trim();
                password = activityLoginBinding.password.getText().toString().trim();
                Toast.makeText(LoginActivity.this, email + " " + password, Toast.LENGTH_SHORT).show();
                //data validation
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Field(s) cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email,password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        //giving user progress
        SweetAlertDialog progressDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.setTitleText("Signing in.. Please wait");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    SweetAlertDialog successDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    successDialog.setTitleText("Account Verified" + task.isSuccessful());
                    successDialog.setCancelable(true);
                    successDialog.show();
                    updateUi();
                } else if (!task.isSuccessful()) {
                    progressDialog.dismiss();
                    SweetAlertDialog errorDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                    errorDialog.setTitleText("Error in signing in");
                    errorDialog.setCancelable(true);
                    errorDialog.setCanceledOnTouchOutside(false);
                    errorDialog.show();
                }
            }
        });
    }

    private void updateUi() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}