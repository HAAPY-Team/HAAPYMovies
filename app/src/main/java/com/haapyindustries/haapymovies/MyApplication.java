package com.haapyindustries.haapymovies;

import android.app.Application;

import com.haapyindustries.haapymovies.models.UserManager;

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
        super.onCreate();
        UserManager.addAdmin("a", "a", "cs");
        UserManager.addUser("j", "j", "cs");
        UserManager.addUser("y", "y", "cs");
        UserManager.addUser("p", "p", "cs");
    }
}
