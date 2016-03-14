package com.haapyindustries.haapymovies.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.Movie;

import java.util.ArrayList;

/**
 * Movie List Activity
 * Lists Movies, and allows users to rate them
 *
 * @author Aaron Andrews
 * @version M8
 */
public class MovieListActivity extends AppCompatActivity {

    private RequestQueue queue;

    private String response;
    private ArrayList<Movie> movies;
    private String apiKey;

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiKey = "yedukp76ffytfuy24zsqk7f5";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue = Volley.newRequestQueue(this);
    }

    /**
     * Handles In Theaters Button clicks
     * gets releases that are in theaters
     *
     * @param view View that was clicked
     */
    public void getReleasesTheater(View view) {
        getReleases("theaterREQ");
    }

    /**
     * Handles New on DVD Button Clicks
     * get releases that have newly released DVDs
     *
     * @param view View that was clicked
     */
    public void getReleasesDVD(View view) {
        getReleases("dvdREQ");
    }

    /**
     * Handles Movie Search Button Clicks
     *
     * @param view View that was clicked
     */
    public void movieSearch(View view) {
        EditText searchbox = (EditText) findViewById(R.id.editText);
        if (!String.valueOf(searchbox.getText()).replace(" ", "").equals("")) {
            getReleases(searchbox.getText().toString());
        }
    }

    /**
     * Gets releases and populates ListView based on certain search criteria
     *
     * @param type of Movies to Populate ListView with
     */
    public void getReleases(String type) {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=yedukp76ffytfuy24zsqk7f5";
        if(type.equals("theaterREQ")) {
            url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=" + apiKey;
        } else if(type.equals("dvdREQ")) {
            url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=" + apiKey;
        } else {
            url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="+apiKey+"&q=" + type.replaceAll("\\s+","%20");
        }

        JsonObjectRequest joR = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject resp) {
                    JSONArray movieArray = resp.optJSONArray("movies");
                    movies = new ArrayList<Movie>();
                    for(int i = 0; i < movieArray.length(); i++){
                        try{
                            JSONObject movieObj = movieArray.getJSONObject(i);
                            Movie m = new Movie();
                            assert movieObj != null;
                            m.setTitle(movieObj.optString("title"));
                            movies.add(m);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayAdapter adapter = new ArrayAdapter(MovieListActivity.this, R.layout.activity_listview, movies);
                    ListView listView = (ListView) findViewById(R.id.mobile_list);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String item = ((TextView) view).getText().toString();
                            goToRater(item);
                        }
                    });

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    response = "Request failed bro";
                }
            });
        queue.add(joR);

    }

    /**
     * Handles Clicks on ListView items
     * Opens a Rater Activity
     *
     * @param movieName Name of Movie that was clicked on
     */
    public void goToRater(String movieName){
        Intent intent = new Intent(this, RaterActivity.class);
        intent.putExtra("movieName", movieName);
        startActivity(intent);
    }
}
