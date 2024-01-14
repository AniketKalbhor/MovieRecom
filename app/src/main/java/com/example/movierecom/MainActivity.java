package com.example.movierecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button Logout;
    EditText Email, Name;
    FirebaseAuth mAuth;
    TextView welcome;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        welcome = findViewById(R.id.userView);
        Logout = findViewById(R.id.logout);
        Email = findViewById(R.id.emailR);
        Name = findViewById(R.id.nameR);
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent toHome;
            toHome = new Intent(MainActivity.this, Login.class);
            startActivity(toHome);
            finish();
        }
        else{
            Name.setText(currentUser.getDisplayName());
            Email.setText(currentUser.getEmail());
            welcome.setText("Hello"+currentUser.getDisplayName()+"!");
        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent toHome;
                toHome = new Intent(MainActivity.this, Login.class);
                startActivity(toHome);
                finish();
            }
        });
    }
}