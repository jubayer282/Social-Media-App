package com.jubayer.socialmedia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    Button logOut;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");*/

        logOut = findViewById(R.id.logOut);
        /*logout firebase*/
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "LogOut Successfully ", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
                finish();
                finishAffinity();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

    }
}