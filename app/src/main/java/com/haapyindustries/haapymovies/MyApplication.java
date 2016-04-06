package com.haapyindustries.haapymovies;

import android.app.Application;

import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;

/**
 * Main Application
 *
 * @author pjztam
 * @version M8
 */
public class MyApplication extends Application{

    /**
     * Creates Application
     *
     * Adds in a bunch of sample Users
     */
    @Override
    public void onCreate() {
        final String major = "cs";
        super.onCreate();

        final Database db = new Database(getApplicationContext());
        UserManager.addAdmin("a", "a", major, db);
    }
}
