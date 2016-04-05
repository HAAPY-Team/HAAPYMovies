package com.haapyindustries.haapymovies;

import android.app.Application;
import android.util.Log;

import com.haapyindustries.haapymovies.models.User;
import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;

import java.sql.SQLException;

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
        try {
            db.open();
        } catch (SQLException e) {
            Log.w("MyApplication", e.getMessage());
        }
        UserManager.addAdmin("a", "a", major, db);
        UserManager.addUser("j", "j", major, db);
        UserManager.addUser("y", "y", major, db);
        UserManager.addUser("p", "p", major, db);
        UserManager.addUser("aaron", "weed", major, db);
        db.addUser(new User("pjztam5", "stuff", major));
        final User u = db.getUserFromUsername("pjztam5");
        Log.d("MyApplication", u.getMajor());
    }
}
