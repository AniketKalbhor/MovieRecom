package com.example.movierecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button Logout, Search;
    EditText Email, Name, Movie;
    FirebaseAuth mAuth;
    TextView welcome;
    FirebaseUser currentUser;
//    private final String url = "http://www.omdbapi.com/?t=";
//    private final String t = "t=";
//    private final String apiKey = "&apikey=c6ac6a3d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        welcome = findViewById(R.id.userView);
        Logout = findViewById(R.id.logout);
        Search = findViewById(R.id.search);
        Email = findViewById(R.id.emailR);
//        Name = findViewById(R.id.nameR);
        Movie = findViewById(R.id.movieName);
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent toHome;
            toHome = new Intent(MainActivity.this, Login.class);
            startActivity(toHome);
            finish();
        }
        else{
//            Name.setText(currentUser.getDisplayName());
            Email.setText(currentUser.getEmail());
//            welcome.setText(new StringBuilder().append("Hello").append(currentUser.getDisplayName()).append("!").toString());
            welcome.setText(new StringBuilder().append("Hello").append(" There").append("!").toString());
        }

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = Movie.getText().toString().trim();
                if(movie.isEmpty()){
                    Toast.makeText(MainActivity.this, "Cannot keep search blank", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, Result.class);
                    intent.putExtra("name",movie);
                    startActivity(intent);
                }
            }
        });
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