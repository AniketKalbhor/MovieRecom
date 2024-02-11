package com.example.movierecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Details extends AppCompatActivity {
    ImageView img;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        info = findViewById(R.id.info);
        img = findViewById(R.id.backdrop);
        Bundle extras = getIntent().getExtras();
        String id = null;
        if (extras != null) {
            id = extras.getString("id");
        }
        String detailURL = "https://api.themoviedb.org/3/movie/" +
                id +
                "?&api_key=9056240ba4b7280ea24f7e700ceadca9";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, detailURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    info.setText(jsonObject.getString("overview"));
                        Glide.with(Details.this)
                                .load("https://image.tmdb.org/t/p/w500" + jsonObject.getString("backdrop_path"))
                                .into(img);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Details.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("VolleyError", String.valueOf(error));
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}