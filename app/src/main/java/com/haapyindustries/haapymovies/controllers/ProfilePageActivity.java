package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.User;
import com.haapyindustries.haapymovies.models.UserManager;

/**
 * Profile Page Activity
 * Shows and allows editing of User Profile
 *
 * @author Yuanhan Pan, pjztam
 * @version M8
 */
public class ProfilePageActivity extends AppCompatActivity {

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        User user = UserManager.getLoggedInUser();
        ((EditText) findViewById(R.id.profilePageMajor)).setText(user.getMajor());
        ((EditText) findViewById(R.id.profilePagePassword)).setText(user.getPassword());
        ((TextView) findViewById(R.id.profilePageBanner)).setText(user.getUsername());
    }

    /**
     * Handles Logout Button clicks
     * Logs user out
     * Shows welcome screen
     *
     * @param w View that was clicked
     */
    public void onLogoutButtonClick(View w) {
        UserManager.logoutUser();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Handles Edit Button Clicks
     * Edits user info
     * Displays "User Edited" Toast
     *
     * @param w View that was clicked
     */
    public void onEditButtonClick(View w) {
        User user = UserManager.getLoggedInUser();
        String newMajor = ((EditText) findViewById(R.id.profilePageMajor)).getText().toString();
        String newPassword = ((EditText) findViewById(R.id.profilePagePassword)).getText().toString();
        user.setMajor(newMajor);
        user.setPassword(newPassword);
        Toast.makeText(getApplicationContext(), "User Edited", Toast.LENGTH_SHORT).show();
    }

}
