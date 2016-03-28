package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;
import com.haapyindustries.haapymovies.models.User;
import com.haapyindustries.haapymovies.models.UserManager;

/**
 * Login Page Activity
 * Shows Login Page and handles button clicks on it
 *
 * @author Yuanhan Pan, Patrick Tam
 * @version M8
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Sets up Activity
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Handles Cancel Button Clicks
     * Cancels login, ends current Activity
     *
     * @param w View that was clicked
     */
    public void onCancelButtonClick(View w) {
        finish();
    }

    /**
     * Handles Login Button clicks
     * Logs in User and goes to profile page on success
     * Displays verious error messages if login fails
     *
     * @param w View that was clicked
     */
    public void onLoginButtonClick(View w) {
        EditText userBox = (EditText) findViewById(R.id.user_box);
        EditText passBox = (EditText) findViewById(R.id.pass_box);
        String username = userBox.getText().toString();
        String password = passBox.getText().toString();

        User user = UserManager.handleLoginRequest(username, password);

        if (user == null) {
            (Toast.makeText(getApplicationContext(), "User Account doesn't exist", Toast.LENGTH_SHORT)).show();
        } else if (user.getUserStatus() == UserStatus.BANNED) {
            (Toast.makeText(getApplicationContext(), "User Account banned", Toast.LENGTH_SHORT)).show();
        } else if (user.getUserStatus() == UserStatus.LOCKED) {
            (Toast.makeText(getApplicationContext(), "User Account locked", Toast.LENGTH_SHORT)).show();
        } else if (!user.getPassword().equals(password)) {
            if (user.getLoginTries() < 3) {
                Log.d("Login Failed", "from login activity");
                (Toast.makeText(getApplicationContext(), "Incorrect Password. You have " + (3 - user.getLoginTries()) + " tries left.", Toast.LENGTH_SHORT)).show();
            }
        } else if (user.getPassword().equals(password)) {
            if(UserManager.getLoggedInUser().getUserType() == UserType.ADMIN) {
                Intent intent = new Intent(this, AdminUserListActivity.class);
                finish();
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, HomeActivity.class);
                finish();
                startActivity(intent);
            }
        }
    }
}
