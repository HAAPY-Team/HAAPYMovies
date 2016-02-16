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
import com.haapyindustries.haapymovies.Welcome;
import com.haapyindustries.haapymovies.models.UserManager;

public class RegistrationPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onCancelButtonClick(View w) {
        finish();
    }

    public void onRegisterButtonClick(View w) {
        String username = ((EditText) findViewById(R.id.registerPageUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.registerPagePassword)).getText().toString();
        String major = ((EditText) findViewById(R.id.registerPageMajor)).getText().toString();
        UserManager um = new UserManager();

        if (um.doesUserExist(username)) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast t = Toast.makeText(context, "User already exists", duration);
            t.show();
        } else {
            um.addUser(username, password, major);
            Intent intent = new Intent(this, Welcome.class);
            finish();
            startActivity(intent);
        }

    }

}
