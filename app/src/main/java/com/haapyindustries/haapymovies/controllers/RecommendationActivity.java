package com.haapyindustries.haapymovies.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.RatingData;
import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;

/**
 * Recommendation Activity
 * Shows recommendations for a given major
 *
 * @author Yuanhan Pan
 * @version M8
 */
public class RecommendationActivity extends AppCompatActivity {
    /**
     * ImageView for the rating element
     */
    private ImageView rec1;
    /**
     * ImageView for the rating element
     */
    private ImageView rec2;
    /**
     * ImageView for the rating element
     */
    private ImageView rec3;
    /**
     * ImageView for the rating element
     */
    private ImageView rec4;
    /**
     * ImageView for the rating element
     */
    private ImageView rec5;
    /**
     * TextView for the recommendation text
     */
    private TextView recommendText;
    /**
     * Spinner to hold all the majors
     */
    private Spinner majorSpinner;

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);
        final Database db = new Database(getBaseContext());
        final String[] majors = db.getMajors();
        db.close();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, majors);
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
        final String major = majorSpinner.getSelectedItem().toString();
        final Database db = new Database(getBaseContext());
        final RatingData topMovie = db.getRecommendationForMajor(major);
        db.close();
        recommendText.setVisibility(View.VISIBLE);
        if (topMovie == null) {
            final String viewText = "No Recommendations Found for any Movies by " + major + " Majors";
            recommendText.setText(viewText);
        } else {
            recommendText.setText(topMovie.getMovie());
            final int rating = topMovie.getRating();
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
