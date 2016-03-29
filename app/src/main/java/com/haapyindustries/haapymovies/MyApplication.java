package com.haapyindustries.haapymovies;

import android.app.Application;
import android.util.Log;

import com.haapyindustries.haapymovies.models.User;
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
        super.onCreate();

        Database db = new Database(getApplicationContext());
        try {
            db.open();
        } catch (Exception e) {
            Log.w("MyApplication", e.getMessage());
        }
        UserManager.addAdmin("a", "a", "cs", db);
        UserManager.addUser("j", "j", "cs", db);
        UserManager.addUser("y", "y", "cs", db);
        UserManager.addUser("p", "p", "cs", db);
        UserManager.addUser("aaron", "weed", "cs", db);
        db.addUser(new User("pjztam5", "stuff", "cs"));
        User u = db.getUserFromUsername("pjztam5");
        Log.d("MyApplication", u.getMajor());
    }
}
