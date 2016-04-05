package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.haapyindustries.haapymovies.R;

/**
 * Home Activity
 * Splash screen shown when a User logs in
 *
 * @author Yuanhan Pan
 * @version M8
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Handles Search Button Clicks
     * Starts MovieListActivity
     *
     * @param v View that was clicked
     */
    public void onSearchButtonClick(View v) {
        final Intent intent = new Intent(this, MovieListActivity.class);
        startActivity(intent);
    }

    /**
     * Handles Recommendation Button Clicks
     * Starts RecommendationActivity
     *
     * @param v View that was clicked
     */
    public void onRecommendButtonClick(View v) {
        final Intent intent = new Intent(this, RecommendationActivity.class);
        startActivity(intent);
    }

    /**
     * Handles Logout Button Clicks
     * Starts WelcomeActivity, finished current Activity
     *
     * @param v View that was clicked
     */
    public void onLogoutButtonClick(View v) {
        final Intent intent = new Intent(this, WelcomeActivity.class);
        finish();
        startActivity(intent);
    }

}
