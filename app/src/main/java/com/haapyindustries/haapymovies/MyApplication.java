package com.haapyindustries.haapymovies;

import android.app.Application;

import com.haapyindustries.haapymovies.models.UserManager;

/**
 * Created by pjztam on 3/13/2016.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        UserManager.addAdmin("admin", "p", "cs");
        UserManager.addUser("jinsong", "j", "cs");
        UserManager.addUser("yuanhan", "y", "cs");
        UserManager.addUser("patrick", "p", "cs");
    }
}
