package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.Button;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.RatingData;
import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;

/**
 * Rater Activity
 * Shows Movie Rating Screen
 * Allows Users to Rate movies and see the average rating
 *
 * @author Henry Leung, Yuanhan Pan
 * @version M8
 */
public class RaterActivity extends AppCompatActivity implements View.OnClickListener {
    //Global identifiers
    /**
     * Spinner for the page
     */
    private Spinner value;
    /**
     * Rating image for the page
     */
    private ImageView rat1;
    /**
     * Rating image for the page
     */
    private ImageView rat2;
    /**
     * Rating image for the page
     */
    private ImageView rat3;
    /**
     * Rating image for the page
     */
    private ImageView rat4;
    /**
     * Rating image for the page
     */
    private ImageView rat5;
    /**
     * Rating image for the page
     */
    private ImageView orat1;
    /**
     * Rating image for the page
     */
    private ImageView orat2;
    /**
     * Rating image for the page
     */
    private ImageView orat3;
    /**
     * Rating image for the page
     */
    private ImageView orat4;
    /**
     * Rating image for the page
     */
    private ImageView orat5;
    /**
     * Submit button for the page
     */
    private Button submit;

    /**
     * Handles Rate button Clicks
     * Stores Rating and updates average rating
     *
     * @param view View that was clicked on
     */
    @Override
    public void onClick(View view) {
        final String userInput = value.getSelectedItem().toString(); //get text from field
        final boolean isNumeric = isNumeric(userInput); //check to see if value is numeric
        if (!isNumeric) { //if value is not numeric toast
            Toast.makeText(getApplicationContext(), "Please input a number!", Toast.LENGTH_SHORT).show();
        } else {
            final int ratingNumber = Integer.parseInt(userInput);
            final Intent intent = getIntent();
            final String major = UserManager.getUserMajor();
            final String username = UserManager.getLoggedInUser().getUsername();
            final String movieName = intent.getStringExtra("movieName");

            final Database db = new Database(getBaseContext());
            RatingData rating = db.getUserRatingForMovie(username, movieName);


            if (rating != null) {
                rating.setRating(ratingNumber);
                db.updatRating(rating);
            } else {
                rating = new RatingData();
                rating.setRating(ratingNumber);
                rating.setMajor(major);
                rating.setUsername(username);
                rating.setMovie(movieName);
                db.addRating(rating);
            }
            db.close();
            //Ratings.addRating(movieName, ratingNumber);
            //cases
            if ("1".equals(userInput)) { //case 1
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.INVISIBLE);
                rat3.setVisibility(View.INVISIBLE);
                rat4.setVisibility(View.INVISIBLE);
                rat5.setVisibility(View.INVISIBLE);

            } else if ("2".equals(userInput)) { //case 2
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.INVISIBLE);
                rat4.setVisibility(View.INVISIBLE);
                rat5.setVisibility(View.INVISIBLE);
            } else if ("3".equals(userInput)) { //case 3
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.VISIBLE);
                rat4.setVisibility(View.INVISIBLE);
                rat5.setVisibility(View.INVISIBLE);
            } else if ("4".equals(userInput)) { //case 4
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.VISIBLE);
                rat4.setVisibility(View.VISIBLE);
                rat5.setVisibility(View.INVISIBLE);
            } else if ("5".equals(userInput)) { //case 5
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.VISIBLE);
                rat4.setVisibility(View.VISIBLE);
                rat5.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getApplicationContext(), "Number must be between 1 and 5!", Toast.LENGTH_SHORT).show(); // if number is not 1 through 5
            }
        }
    }

    /**
     * Sets up Activity
     * Displays current Movie rating
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        final String movieName = intent.getStringExtra("movieName");
        final String major = UserManager.getUserMajor();
        final String username = UserManager.getLoggedInUser().getUsername();
        final Database db = new Database(getBaseContext());

        final int orating = db.getMajorRatingForMovie(major, movieName);
        final RatingData userRating = db.getUserRatingForMovie(username, movieName);
        int urating = 0;
        if (userRating != null) {
            urating = userRating.getRating();
        }
        db.close();
        //RatingData orating = Ratings.getOverallRating(movieName);
        //Integer urating = Ratings.getUserRating(movieName);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rater);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //load views
        value = (Spinner) findViewById(R.id.ratingSpinner);
        rat1 = (ImageView) findViewById(R.id.rat1);
        rat2 = (ImageView) findViewById(R.id.rat2);
        rat3 = (ImageView) findViewById(R.id.rat3);
        rat4 = (ImageView) findViewById(R.id.rat4);
        rat5 = (ImageView) findViewById(R.id.rat5);
        final String[] ratings = {"1", "2", "3", "4", "5"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ratings);
        value.setAdapter(adapter);
        final TextView movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieTitle.setText(movieName);

        orat1 = (ImageView) findViewById(R.id.orat1);
        orat2 = (ImageView) findViewById(R.id.orat2);
        orat3 = (ImageView) findViewById(R.id.orat3);
        orat4 = (ImageView) findViewById(R.id.orat4);
        orat5 = (ImageView) findViewById(R.id.orat5);
        submit = (Button) findViewById(R.id.ratingButton);
        setSupportActionBar(toolbar);

        if (orating != 0) {
            switch (orating) {
                case 1:
                    orat1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    orat1.setVisibility(View.VISIBLE);
                    orat2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    orat1.setVisibility(View.VISIBLE);
                    orat2.setVisibility(View.VISIBLE);
                    orat3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    orat1.setVisibility(View.VISIBLE);
                    orat2.setVisibility(View.VISIBLE);
                    orat3.setVisibility(View.VISIBLE);
                    orat4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    orat1.setVisibility(View.VISIBLE);
                    orat2.setVisibility(View.VISIBLE);
                    orat3.setVisibility(View.VISIBLE);
                    orat4.setVisibility(View.VISIBLE);
                    orat5.setVisibility(View.VISIBLE);
                    break;
            }
        }

        if (urating != 0) {
            switch (urating) {
                case 1:
                    rat1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    rat1.setVisibility(View.VISIBLE);
                    rat2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    rat1.setVisibility(View.VISIBLE);
                    rat2.setVisibility(View.VISIBLE);
                    rat3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    rat1.setVisibility(View.VISIBLE);
                    rat2.setVisibility(View.VISIBLE);
                    rat3.setVisibility(View.VISIBLE);
                    rat4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    rat1.setVisibility(View.VISIBLE);
                    rat2.setVisibility(View.VISIBLE);
                    rat3.setVisibility(View.VISIBLE);
                    rat4.setVisibility(View.VISIBLE);
                    rat5.setVisibility(View.VISIBLE);
                    break;
            }
        }

        submit.setOnClickListener(this); //set action listner
    }

    /**
     * Validates Ratings
     *
     * @param str String to validate
     * @return True if the String is Numeric, False otherwise
     */
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }


}
