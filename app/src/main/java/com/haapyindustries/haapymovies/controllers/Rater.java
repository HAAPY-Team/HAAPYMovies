package com.haapyindustries.haapymovies.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.Button;

import com.haapyindustries.haapymovies.R;

public class Rater extends AppCompatActivity implements View.OnClickListener {
    //Global identifiers
    EditText value;
    ImageView rat1;
    ImageView rat2;
    ImageView rat3;
    ImageView rat4;
    ImageView rat5;
    Button submit;

    @Override
    public void onClick(View view) {
        String userInput = String.valueOf(value.getText()); //get text from field
        boolean isNumeric = isNumeric(userInput); //check to see if value is numeric
        if (!isNumeric) { //if value is not numeric toast
            Toast.makeText(getApplicationContext(), "Please input a number!", Toast.LENGTH_SHORT).show();
        }
        else {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rater);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //load views
        value = (EditText) findViewById(R.id.ratingField);
        rat1 = (ImageView) findViewById(R.id.rat1);
        rat2 = (ImageView) findViewById(R.id.rat2);
        rat3 = (ImageView) findViewById(R.id.rat3);
        rat4 = (ImageView) findViewById(R.id.rat4);
        rat5 = (ImageView) findViewById(R.id.rat5);
        submit = (Button) findViewById(R.id.ratingButton);
        setSupportActionBar(toolbar);

        submit.setOnClickListener(this); //set action listner
    }

    private static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
