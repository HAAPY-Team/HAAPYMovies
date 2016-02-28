package com.haapyindustries.haapymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.haapyindustries.haapymovies.controllers.LoginActivity;
import com.haapyindustries.haapymovies.controllers.RegistrationPageActivity;
import com.haapyindustries.haapymovies.models.UserManager;

/**
 * Welcome Page Activity
 * displays welcome page and handles associated button clicks
 *
 * @version M4
 * @author Yuanhan Pan, pjztam
 */
public class Welcome extends AppCompatActivity {

    /**
     * Sets up Welcome Page
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * Handles Login Button clicks
     * goes to Login page
     *
     * @param w
     */
    public void onLoginButtonClick(View w) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Handles Registration Button clicks
     * goes to Registration page
     * @param w
     */
    public void onRegistrationButtonClick(View w) {
        Intent intent = new Intent(this, RegistrationPageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("**MYAPP**", "Pausing the opening screen");
    }

    public void onResume() {
        super.onResume();
        Log.d("**MYAPP**", "Resuming the opening screen");
        System.out.println("resuming");
    }


}
