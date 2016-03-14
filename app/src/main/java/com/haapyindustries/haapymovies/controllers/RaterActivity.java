package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.Button;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.RatingData;
import com.haapyindustries.haapymovies.models.Ratings;
import com.haapyindustries.haapymovies.models.UserManager;

public class RaterActivity extends AppCompatActivity implements View.OnClickListener {
    //Global identifiers
    Spinner value;
    ImageView rat1;
    ImageView rat2;
    ImageView rat3;
    ImageView rat4;
    ImageView rat5;
    ImageView orat1;
    ImageView orat2;
    ImageView orat3;
    ImageView orat4;
    ImageView orat5;
    Button submit;

    @Override
    public void onClick(View view) {
        String userInput = value.getSelectedItem().toString(); //get text from field
        boolean isNumeric = isNumeric(userInput); //check to see if value is numeric
        if (!isNumeric) { //if value is not numeric toast
            Toast.makeText(getApplicationContext(), "Please input a number!", Toast.LENGTH_SHORT).show();
        }
        else {
            int ratingNumber = Integer.parseInt(userInput);
            Intent intent = getIntent();
            String movieName = intent.getStringExtra("movieName");
            Ratings.addRating(movieName, ratingNumber);
            //cases
            if (userInput.equals("1")) { //case 1
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.INVISIBLE);
                rat3.setVisibility(View.INVISIBLE);
                rat4.setVisibility(View.INVISIBLE);
                rat5.setVisibility(View.INVISIBLE);

            }
            else if (userInput.equals("2")) { //case 2
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.INVISIBLE);
                rat4.setVisibility(View.INVISIBLE);
                rat5.setVisibility(View.INVISIBLE);
            }
            else if (userInput.equals("3")) { //case 3
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.VISIBLE);
                rat4.setVisibility(View.INVISIBLE);
                rat5.setVisibility(View.INVISIBLE);
            }
            else if (userInput.equals("4")) { //case 4
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.VISIBLE);
                rat4.setVisibility(View.VISIBLE);
                rat5.setVisibility(View.INVISIBLE);
            }
            else if (userInput.equals("5")) { //case 5
                rat1.setVisibility(View.VISIBLE);
                rat2.setVisibility(View.VISIBLE);
                rat3.setVisibility(View.VISIBLE);
                rat4.setVisibility(View.VISIBLE);
                rat5.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(getApplicationContext(), "Number must be between 1 and 5!", Toast.LENGTH_SHORT).show(); // if number is not 1 through 5
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movieName");
        RatingData orating = Ratings.getOverallRating(movieName);
        Integer urating = Ratings.getUserRating(movieName);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rater);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //load views
        value = (Spinner) findViewById(R.id.ratingSpinner);
        rat1 = (ImageView) findViewById(R.id.rat1);
        rat2 = (ImageView) findViewById(R.id.rat2);
        rat3 = (ImageView) findViewById(R.id.rat3);
        rat4 = (ImageView) findViewById(R.id.rat4);
        rat5 = (ImageView) findViewById(R.id.rat5);
        String[] ratings = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ratings);
        value.setAdapter(adapter);
        TextView movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieTitle.setText(movieName);

        orat1 = (ImageView) findViewById(R.id.orat1);
        orat2 = (ImageView) findViewById(R.id.orat2);
        orat3 = (ImageView) findViewById(R.id.orat3);
        orat4 = (ImageView) findViewById(R.id.orat4);
        orat5 = (ImageView) findViewById(R.id.orat5);
        submit = (Button) findViewById(R.id.ratingButton);
        setSupportActionBar(toolbar);

        if (orating != null) {
            switch (orating.currentRating) {
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

        if (urating != null) {
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

    private static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
