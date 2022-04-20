package com.example.androidip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.androidip.databinding.ActivityLoginBinding;
import com.example.androidip.databinding.ActivitySignupBinding;
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
    private Button login;

    private ActivityLoginBinding activityLoginBinding;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    ProgressDialog progressDialog;

    public static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);


        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {

                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }
            }

        };

        activityLoginBinding.clContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = activityLoginBinding.tvLoginEmail.getText().toString().trim();
                password = activityLoginBinding.tvLoginPassword.getText().toString().trim();

                Toast.makeText(LoginActivity.this, email + " " + password, Toast.LENGTH_SHORT).show();
                //data validation
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Field(s) cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    loginWithPassword(email,password);
                }

            }
        });
        activityLoginBinding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

    }

    private void loginWithPassword(String email, String password) {


        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Signing In..."); //show this while waiting. Be sure to dismiss() after
        progressDialog.show();

        //Login using built-in Firebase method signInWithEmailAndPassword
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        progressDialog.dismiss();


                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithEmail", task.getException());

                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }

                    }

                });

    }

    @Override
    public void onStart() {

        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);

    }

    @Override
    public void onStop() {

        super.onStop();

        if (firebaseAuthListener != null) {

            firebaseAuth.removeAuthStateListener(firebaseAuthListener);

        }

    }
}