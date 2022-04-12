package com.example.androidip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidip.databinding.ActivityLoginBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText uname;
    private Button start;

    private static final String TAG = "LoginActivity";
    int AUTHUI_REQUEST_CODE = 1314;

    //Binding Class
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Layout
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);

        //Check if user is logged in
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        activityLoginBinding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();

                String string = activityLoginBinding.uname.getText().toString();

                //Go to next layout
                Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                intent.putExtra("username", string);
                startActivity(intent);
            }
        });
    }

    public void handleLoginRegister(View view) {
        List<AuthUI.IdpConfig> provider = Arrays.asList( new AuthUI.IdpConfig.EmailBuilder().build());

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .setTosAndPrivacyPolicyUrls("", "")
                .setLogo(R.drawable.logo)
                .setAlwaysShowSignInMethodScreen(true)
                .build();

                startActivityForResult(intent, AUTHUI_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHUI_REQUEST_CODE){
            if (requestCode == RESULT_OK){
                //We have signed in or have a new user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "onActivityResult: " + user.getEmail());
                    if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()){
                        Toast.makeText(this, "Welcome new User", Toast.LENGTH_SHORT).show();
                    }else {
                        //This is a returning user
                        Toast.makeText(this, "Welcome Back Again", Toast.LENGTH_SHORT).show();
                    }
                Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent)
                    this.finish();

            } else {
                //sign in failed
                IdpResponse response = IdpResponse.fromResultIntent(data);
                
                if (response == null){
                    Log.d(TAG, "onActivityResult:  the user has cancelled sign in request");
                } else {
                    Log.e(TAG, "onActivityResult: ", response.getError() );
                }
            }
        }
    }
}
