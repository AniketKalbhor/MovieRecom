package com.example.movierecom;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
//    Button Login, RegisterBtn, googleSignIn, Guest;
    Button Login, googleSignIn, Guest;
    EditText RegisterBtn;
    EditText Email, Pswd;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent toHome;
            toHome = new Intent(Login.this, MainActivity.class);
            startActivity(toHome);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Login = findViewById(R.id.login);
        googleSignIn = findViewById(R.id.googleSignIn);
        Guest = findViewById(R.id.guest);
        RegisterBtn = findViewById(R.id.register);
        Email = findViewById(R.id.emailL);
        Pswd = findViewById(R.id.passwordL);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent toRegister;
                toRegister = new Intent(com.example.movierecom.Login.this, Register.class);
                startActivity(toRegister);
                progressBar.setVisibility(View.GONE);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Pswd.getText());
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter all Credentials", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
//                if(TextUtils.isEmpty(password)) {
//                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }
                else{
                    Toast.makeText(Login.this, "Checking your account", Toast.LENGTH_SHORT).show();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                        Log.d(TAG, "signInWithEmail:success");
//                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Login.this, "Authentication Successful",
                                                Toast.LENGTH_SHORT).show();
                                        Intent toHome;
                                        toHome = new Intent(Login.this, MainActivity.class);
                                        startActivity(toHome);
                                        finish();
                                    } else {
//                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}