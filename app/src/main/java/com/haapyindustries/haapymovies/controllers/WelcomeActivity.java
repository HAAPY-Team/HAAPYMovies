package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.controllers.LoginActivity;
import com.haapyindustries.haapymovies.controllers.RegistrationPageActivity;
import com.haapyindustries.haapymovies.models.UserManager;

/**
 * Welcome Activity
 * displays welcome page and handles associated button clicks
 *
 * @version M8
 * @author Yuanhan Pan, pjztam
 */
public class WelcomeActivity extends AppCompatActivity {

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        UserManager.addMajor("Computer Science");
        UserManager.addMajor("Math");
    }

    /**
     * Handles Login Button clicks
     * goes to Login page
     *
     * @param w View that was clicked
     */
    public void onLoginButtonClick(View w) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Handles Registration Button clicks
     * goes to Registration page
     *
     * @param w View that was clicked
     */
    public void onRegistrationButtonClick(View w) {
        Intent intent = new Intent(this, RegistrationPageActivity.class);
        startActivity(intent);
    }
}
