package com.example.movierecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
//    FirebaseAuth mAuth;
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent toHome;
//            toHome = new Intent(Login.this, MainActivity.class);
//            startActivity(toHome);
//            finish();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent goRegister= new Intent(Splash.this, Login.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(goRegister);
                finish();
            }
        }, 4000);
    }
}