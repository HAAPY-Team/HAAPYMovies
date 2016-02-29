package com.haapyindustries.haapymovies.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.Movie;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    private RequestQueue queue;

    private String response;
    private ArrayList<Movie> movies;
    private String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiKey = "yedukp76ffytfuy24zsqk7f5";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue = Volley.newRequestQueue(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button newRelease = (Button) findViewById(R.id.button3);
        Button newDVD = (Button) findViewById(R.id.button4);
        Log.d("created", "created");
//        newRelease.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getReleasesTheater(view);
//                Log.d("weed", movies.get(0).getTitle());
//
//                ArrayAdapter adapter = new ArrayAdapter(MovieListActivity.this, R.layout.content_movie_list, movies);
//                listView.setAdapter(adapter);
//
//            }
//        });


    }
    public void getReleasesTheater(View view) {
        getReleases("theaterREQ");
    }
    public void getReleasesDVD(View view) {
        getReleases("dvdREQ");
    }
    public void movieSearch(View view) {
        EditText searchbox = (EditText) findViewById(R.id.editText);
        if (!String.valueOf(searchbox.getText()).replace(" ", "").equals("")) {
            getReleases(searchbox.getText().toString());
        }
    }
    public void getReleases(String type) {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5";
        if(type == "theaterREQ") {
            url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=" + apiKey;
        } else if(type == "dvdREQ") {
            url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=" + apiKey;
        } else {
            url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="+apiKey+"&q=" + type.replaceAll("\\s+","%20");
        }
        Log.d("shit", "shit is happening");

        JsonObjectRequest joR = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        Log.d("something", "something is happening");
//                        JSONObject moviesJSON = null;
//                        try {
//                            moviesJSON = resp.getJSONObject("RestResponse");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.d("RESTERROR", "GET FAILED");
//                        }

                        JSONArray movieArray = resp.optJSONArray("movies");
                        movies = new ArrayList<Movie>();
                        for(int i = 0; i < movieArray.length(); i++){
                            try{
                                JSONObject movieObj = movieArray.getJSONObject(i);
                                Movie m = new Movie();
                                assert movieObj != null;
                                m.setTitle(movieObj.optString("title"));
                                Log.d("movie", m.toString() + " added");
                                movies.add(m);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("RESTERROR", "MOVIE GET FAILED");
                            }
                        }
                        ArrayAdapter adapter = new ArrayAdapter(MovieListActivity.this, R.layout.activity_listview, movies);
                        ListView listView = (ListView) findViewById(R.id.mobile_list);
                        listView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        response = "Request failed bro";
                        Log.d("RESTERROR", "RESPONSE FAILED");
                    }
                });
        queue.add(joR);

    }
    public void goToRater(View view){
        Intent intent = new Intent(this, Rater.class);
        startActivity(intent);
    }
}
