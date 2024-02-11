package com.example.movierecom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity implements SelectMovie {
    List<MovieModalClass> movielist;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        movielist = new ArrayList<>();
        recyclerView = findViewById(R.id.resultView);
        getData();
    }

    private void getData() {
        Bundle extras = getIntent().getExtras();
        String value = null;
        if (extras != null) {
            value = extras.getString("name");
        }
        String URL = "https://api.themoviedb.org/3/search/movie?query=" +
                value +
                "&api_key=9056240ba4b7280ea24f7e700ceadca9";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        MovieModalClass model = new MovieModalClass();
                        model.setTitle(jsonObject1.getString("title"));
                        model.setOverview(jsonObject1.getString("overview"));
                        model.setImg(jsonObject1.getString("poster_path"));
                        model.setRating(jsonObject1.getString("vote_average"));
                        model.setId(jsonObject1.getString("id"));

                        movielist.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                PutDataIntoRecyclerView(movielist);
                Adapter adapter = new Adapter(Result.this, movielist, Result.this::onItemClick);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Result.this));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Result.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("VolleyError", String.valueOf(error));
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

//    private void PutDataIntoRecyclerView(List<MovieModalClass> movielist, SelectMovie selectMovie) {
//    }

    @Override
    public void onItemClick(int position) {
        Intent details = new Intent(Result.this, Details.class);
        details.putExtra("id", movielist.get(position).getId());
        Toast.makeText(Result.this, "ID is: "+ movielist.get(position).getId(), Toast.LENGTH_SHORT).show();
        startActivity(details);

    }
}