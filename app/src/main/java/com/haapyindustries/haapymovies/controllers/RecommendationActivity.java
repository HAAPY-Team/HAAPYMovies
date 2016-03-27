package com.haapyindustries.haapymovies.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.RatingData;
import com.haapyindustries.haapymovies.models.Ratings;
import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;

import java.util.ArrayList;

/**
 * Recommendation Activity
 * Shows recommendations for a given major
 *
 * @author Yuanhan Pan
 * @version M8
 */
public class RecommendationActivity extends AppCompatActivity {

    ImageView rec1;
    ImageView rec2;
    ImageView rec3;
    ImageView rec4;
    ImageView rec5;
    TextView recommendText;
    Spinner majorSpinner;

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, UserManager.getMajors());
        majorSpinner.setAdapter(adapter);
        setSupportActionBar(toolbar);

        rec1 = (ImageView) findViewById(R.id.rec1);
        rec2 = (ImageView) findViewById(R.id.rec2);
        rec3 = (ImageView) findViewById(R.id.rec3);
        rec4 = (ImageView) findViewById(R.id.rec4);
        rec5 = (ImageView) findViewById(R.id.rec5);
        recommendText = (TextView) findViewById(R.id.recommendTitle);

        rec1.setVisibility(View.INVISIBLE);
        rec2.setVisibility(View.INVISIBLE);
        rec3.setVisibility(View.INVISIBLE);
        rec4.setVisibility(View.INVISIBLE);
        rec5.setVisibility(View.INVISIBLE);
        recommendText.setVisibility(View.INVISIBLE);
    }

    /**
     * Handles Recommendation Button Clicks
     * Shows a recommended movie based on a given Major
     *
     * @param v View that was clicked
     */
    public void onGetRecommendationButtonClick(View v) {
        rec1.setVisibility(View.INVISIBLE);
        rec2.setVisibility(View.INVISIBLE);
        rec3.setVisibility(View.INVISIBLE);
        rec4.setVisibility(View.INVISIBLE);
        rec5.setVisibility(View.INVISIBLE);
        recommendText.setVisibility(View.INVISIBLE);
        String major = majorSpinner.getSelectedItem().toString();
        Database db = new Database(getBaseContext());
        RatingData topMovie = db.getRecommendationForMajor(major);
        db.close();
        recommendText.setVisibility(View.VISIBLE);
        if (topMovie == null) {
            recommendText.setText("No Recommendations Found for any Movies by " + major + " Majors");
        } else {
            recommendText.setText(topMovie.getMovie());
            int rating = topMovie.getRating();
            switch (rating) {
                case 1:
                    rec1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    rec1.setVisibility(View.VISIBLE);
                    rec2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    rec1.setVisibility(View.VISIBLE);
                    rec2.setVisibility(View.VISIBLE);
                    rec3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    rec1.setVisibility(View.VISIBLE);
                    rec2.setVisibility(View.VISIBLE);
                    rec3.setVisibility(View.VISIBLE);
                    rec4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    rec1.setVisibility(View.VISIBLE);
                    rec2.setVisibility(View.VISIBLE);
                    rec3.setVisibility(View.VISIBLE);
                    rec4.setVisibility(View.VISIBLE);
                    rec5.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

}
