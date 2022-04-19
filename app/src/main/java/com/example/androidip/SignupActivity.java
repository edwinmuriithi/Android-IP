package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.androidip.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    //Binding Class
    private ActivitySignupBinding activitySignupBinding;
    public static final String TAG = SignupActivity.class.getSimpleName();

    private String userName;

    private FirebaseAuth firebaseAuth;
    String Semail,Spassword, Sname, Scpassword ;


    private FirebaseAuth.AuthStateListener firebaseAuthListener;  /* AuthStateListener simply listens for an account being successfully authenticated, or un-authenticated through Firebase. Firebase can also automatically authenticate user accounts upon registration. Therefore, our users can submit the registration form and if their account is created successfully they will be logged in automatically, and this listener will be triggered.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activitySignupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = activitySignupBinding.getRoot();
        setContentView(view);

        activitySignupBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });

        activitySignupBinding.login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); /*FLAG_ACTIVITY_CLEAR_TASK will cause any existing task that would be associated with the activity to be cleared before the activity is started. This prevents this Activity from being unnecessarily accessed via the system back button. FLAG_ACTIVITY_NEW_TASK will make the activity we are navigating to the start of a brand new task on this history stack.*/
                startActivity(intent);
                finish();
            }
        });

            firebaseAuth=FirebaseAuth.getInstance();

            createAuthStateListener();

        }

        private void createAuthStateListener() {

            firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                    final FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();

                    if (firebaseUser!=null){

                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }

                }
            };

        }

        private void createNewUser() {

            Sname = activitySignupBinding.Sname.getText().toString().trim();
            Spassword = activitySignupBinding.Spassword.getText().toString().trim();
            Scpassword = activitySignupBinding.Scpassword.getText().toString().trim();
            Semail = activitySignupBinding.Semail.getText().toString().trim();

            boolean validEmail = isValidEmail(Semail);
            boolean validUserName = isValidUserName(Sname);
            boolean validPassword = isValidPassword(Spassword, Scpassword);
            if (!validEmail || !validUserName || !validPassword) return;

            showProgressBar();

            firebaseAuth.createUserWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(this, task -> {

                if (task.isSuccessful()){

                    Log.d(TAG, "Firebase Authentication is successful.");

                    hideProgressBar();

                    createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));

                    Toast.makeText(SignupActivity.this, "Firebase Authentication is successful.", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(SignupActivity.this, "Firebase Authentication has failed.", Toast.LENGTH_SHORT).show();

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

        private boolean isValidUserName(String name) {

            if (name.equals("")) {

                activitySignupBinding.Sname.setError("Please enter your name");
                return false;

            }

            return true;

        }

        private boolean isValidEmail(String email) {

            boolean isGoodEmail =(email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());

            if (!isGoodEmail) {

                activitySignupBinding.Semail.setError("Please enter a valid email address");
                return false;

            }
            return isGoodEmail;

        }

        private boolean isValidPassword(String password, String confirmPassword) {

            if (password.length() < 6) {

                activitySignupBinding.Spassword.setError("Please create a password containing at least 6 characters");
                return false;

            } else if (!password.equals(confirmPassword)) {

                activitySignupBinding.Scpassword.setError("Passwords do not match");
                return false;

            }

            return true;

        }

        private void showProgressBar() {

//        pbSignInProgressBar.setVisibility(View.VISIBLE);
//        tvLoadingSignUp.setVisibility(View.VISIBLE);
//        tvLoadingSignUp.setText("Give us a second to set up your account.");

        }

        private void hideProgressBar() {

//        pbSignInProgressBar.setVisibility(View.GONE);
//        tvLoadingSignUp.setVisibility(View.GONE);

        }

        private void createFirebaseUserProfile(final FirebaseUser firebaseUser) {

            UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder().setDisplayName(Sname).build();

            firebaseUser.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    if (task.isSuccessful()) {

                        Log.d(TAG, Objects.requireNonNull(firebaseUser.getDisplayName()));

                        String inputFirebaseUserName = Objects.requireNonNull(firebaseUser.getDisplayName());

                        Toast.makeText(SignupActivity.this, "The display name has been set", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignupActivity.this, SearchActivity.class);
                        intent.putExtra("inputFirebaseUserName", inputFirebaseUserName);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); /*FLAG_ACTIVITY_CLEAR_TASK will cause any existing task that would be associated with the activity to be cleared before the activity is started. This prevents this Activity from being unnecessarily accessed via the system back button. FLAG_ACTIVITY_NEW_TASK will make the activity we are navigating to the start of a brand new task on this history stack.*/
                        startActivity(intent);

                        finish();

                    }

                }

            });

        }

}
