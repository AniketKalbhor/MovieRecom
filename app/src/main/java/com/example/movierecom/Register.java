package com.example.movierecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    LottieAnimationView loading;
    EditText Email, Pswd, Name;
    CardView cv;
    Button RegisterBtn;
    FirebaseAuth mAuth;

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent toHome;
            toHome = new Intent(Register.this, MainActivity.class);
            startActivity(toHome);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        RegisterBtn = findViewById(R.id.register);
        Email = findViewById(R.id.emailR);
        Name = findViewById(R.id.nameR);
        Pswd = findViewById(R.id.passwordR);
    loading = findViewById(R.id.load);
        cv = findViewById(R.id.cardView);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.setVisibility(View.GONE);
                RegisterBtn.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String email, password, name;
                        email = String.valueOf(Email.getText());
                        password = String.valueOf(Pswd.getText());
                        name = String.valueOf(Name.getText());
                        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                            Toast.makeText(Register.this, "Enter all Credentials", Toast.LENGTH_SHORT).show();
                        } else {
                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "Account Created",
                                                        Toast.LENGTH_SHORT).show();
                                                //                                        FirebaseUser user = mAuth.getCurrentUser();
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(Register.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            Intent toLogin;
                            toLogin = new Intent(Register.this, Login.class);
                            startActivity(toLogin);
                        }
                        loading.cancelAnimation();
                        loading.setVisibility(View.GONE);
                        cv.setVisibility(View.VISIBLE);
                        RegisterBtn.setVisibility(View.VISIBLE);
                    }

                }, 1500);
            }
        });
    }
}