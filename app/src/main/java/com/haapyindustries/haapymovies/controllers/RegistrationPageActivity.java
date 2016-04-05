package com.haapyindustries.haapymovies.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;

/**
 * Registration Page Activity
 * Shows Registration Page and handles button clicks
 *
 * @author pjztam
 */
public class RegistrationPageActivity extends AppCompatActivity {

    /**
     * Sets up Registration Page
     *
     * @param savedInstanceState the saved instance state of the database to check
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Cancel Registration
     * ends activity
     *
     * @param w the View of the activity
     */
    public void onCancelButtonClick(View w) {
        finish();
    }

    /**
     * Registers User
     * Displays Toast message if User is already in database
     *
     * @param w the View for the activity
     */
    public void onRegisterButtonClick(View w) {
        final String username = ((EditText) findViewById(R.id.registerPageUsername)).getText().toString();
        final String password = ((EditText) findViewById(R.id.registerPagePassword)).getText().toString();
        final String major = ((EditText) findViewById(R.id.registerPageMajor)).getText().toString();
        final Database db = new Database(getBaseContext());
        if (UserManager.doesUserExist(username, db)) {
            final Context context = getApplicationContext();
            final int duration = Toast.LENGTH_SHORT;
            final Toast t = Toast.makeText(context, "User already exists", duration);
            t.show();
        } else {
            UserManager.addUser(username, password, major, db);
            final Intent intent = new Intent(this, WelcomeActivity.class);
            finish();
            startActivity(intent);
        }

    }

}
