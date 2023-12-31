package com.jubayer.socialmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ProgressDialog dialog;
    ImageView logo_img;
    TextView logoText, sloganText;
    private EditText emailTV, passwordTV;
    Button SignUp, login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo_img = findViewById(R.id.logo_img);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        emailTV = findViewById(R.id.email);
        passwordTV = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        SignUp = findViewById(R.id.SignUp);

        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading....");

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();

        }

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                Toast.makeText(LoginActivity.this, "Welcome to Registration now !", Toast.LENGTH_SHORT).show();
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(logo_img, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(emailTV, "username_tran");
                pairs[4] = new Pair<View, String>(passwordTV, "password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                pairs[6] = new Pair<View, String>(SignUp, "login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   startActivity(new Intent(Login.this, MainActivity.class));
                Toast.makeText(Login.this, "LogIn Successfully Done !", Toast.LENGTH_SHORT).show();*/
                LoginUser();
            }
        });
    }
    private Boolean validateEmail() {
        String val = emailTV.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";

        if (val.isEmpty()) {
            emailTV.setError("Field cannot be Empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            return false;
        } else {
            emailTV.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = passwordTV.getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";

        if (val.isEmpty()) {
            passwordTV.setError("Field cannot be Empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            passwordTV.setError("Password is too weak");
            return false;
        } else {
            passwordTV.setError(null);
            return true;
        }

    }

    private void LoginUser() {
        String email = emailTV.getText().toString().trim();
        String pass = passwordTV.getText().toString().trim();

        if (email.isEmpty()) {
            emailTV.setError("Email is required");
            emailTV.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            passwordTV.setError("Password must be 8 character");
            passwordTV.requestFocus();
            return;
        }
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading....");

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                    dialog = new ProgressDialog(LoginActivity.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("Loading....");
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "login un-successful", Toast.LENGTH_SHORT).show();
                    dialog = new ProgressDialog(LoginActivity.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("Loading....");
                }
            }
        });

    }

}