package com.haapyindustries.haapymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.haapyindustries.haapymovies.controllers.LoginActivity;
import com.haapyindustries.haapymovies.controllers.RegistrationPageActivity;
import com.haapyindustries.haapymovies.models.UserManager;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        UserManager um = new UserManager();
        um.addUser("Patrick", "password");
    }

    public void onLoginButtonClick(View w) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onRegistrationnButtonClick(View w) {
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
