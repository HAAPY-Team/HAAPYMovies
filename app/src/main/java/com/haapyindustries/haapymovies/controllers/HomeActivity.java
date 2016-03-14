package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.haapyindustries.haapymovies.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onSearchButtonClick(View v) {
        Intent intent = new Intent(this, MovieListActivity.class);
        startActivity(intent);
    }

    public void onRecommendButtonClick(View v) {
        Intent intent = new Intent(this, RecommendationActivity.class);
        startActivity(intent);
    }

    public void onLogoutButtonClick(View v) {
        Intent intent = new Intent(this, Welcome.class);
        finish();
        startActivity(intent);
    }

}
