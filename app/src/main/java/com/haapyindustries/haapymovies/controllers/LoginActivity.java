package com.haapyindustries.haapymovies.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.UserManager;

/**
 * Login Page Activity
 * Shows Login Page and handles button clicks on it
 *
 * @author Yuanhan Pan
 * @version M4
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Sets up Login page
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Cancels login, ends activity
     *
     * @param w
     */
    public void onCancelButtonClick(View w) {
        finish();
    }

    /**
     * Logs in User and goes to profile page on success
     * Displays "Login Failed" Toast on failure
     *
     * @param w
     */
    public void onLoginButtonClick(View w) {
        EditText userBox = (EditText) findViewById(R.id.user_box);
        EditText passBox = (EditText) findViewById(R.id.pass_box);
        String username = userBox.getText().toString();
        String password = passBox.getText().toString();
        if (UserManager.handleLoginRequest(username, password)) {
            Intent intent = new Intent(this, ProfilePageActivity.class);
            finish();
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, "Login Failed", duration);
            t.show();
        }
    }

}
