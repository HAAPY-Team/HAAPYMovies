package com.haapyindustries.haapymovies.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Database Helper Service Provider
 * handles connections to the Database
 * performs special actions like create/upgrade db
 *
 * @author pjztam, Yuanhan Pan
 * @version M9
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String  USER_TABLE_NAME          = "Users";
    public static final String  USER_COLUMN_UID          = "uid";
    public static final String  USER_COLUMN_USERNAME     = "username";
    public static final String  USER_COLUMN_PASSWORD     = "password";
    public static final String  USER_COLUMN_MAJOR        = "major";
    public static final String  USER_COLUMN_USERTYPE     = "user_type";
    public static final String  USER_COLUMN_USERSTATUS   = "user_status";
    public static final String  USER_COLUMN_LOGINTRIES   = "login_tries";

    public static final String RATINGS_TABLE_NAME = "Ratings";
    public static final String RATINGS_COLUMN_RID = "rid";
    public static final String RATINGS_COLUMN_USERNAME = "username";
    public static final String RATINGS_COLUMN_MAJOR = "major";
    public static final String RATINGS_COLUMN_RATING = "rating";
    public static final String RATINGS_COLUMN_MOVIENAME = "movie_name";

    private static final String DATABASE_NAME            = "haapymovies.db";
    private static final int    DATABASE_VERSION         = 2;

    /**
     * Creates DatabaseHelper
     * just calls the super class constructor
     *
     * @param context currrent Context
     */
    public DatabaseHelper(Context context) {
        this(context, DATABASE_NAME);
    }

    public DatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    /**
     * Sets up Database (if it hasn't been initialized)
     * creates a bunch of Tables
     *
     * @param db empty SQLite database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DatabaseHelper.class.getName(), "Creating Database");
        db.execSQL(
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
            USER_COLUMN_UID         + " integer primary key autoincrement," +
            USER_COLUMN_USERNAME    + " text not null unique," +
            USER_COLUMN_PASSWORD    + " text not null," +
            USER_COLUMN_MAJOR       + " text not null," +
            USER_COLUMN_USERTYPE    + " text not null," +
            USER_COLUMN_USERSTATUS  + " text not null," +
            USER_COLUMN_LOGINTRIES  + " int" +
            ");"
        );
        db.execSQL(
                "CREATE TABLE " + RATINGS_TABLE_NAME + " (" +
                        RATINGS_COLUMN_RID + " integer primary key autoincrement," +
                        RATINGS_COLUMN_USERNAME + " text not null," +
                        RATINGS_COLUMN_MOVIENAME + " text not null," +
                        RATINGS_COLUMN_MAJOR + " text not null," +
                        RATINGS_COLUMN_RATING + " int" +
                        ");"
        );
    }

    /**
     * Upgrades Database
     * just trashes the database if there is a new version lol
     *
     * @param db old Database you're upgrading
     * @param oldVersion number
     * @param newVersion number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseHelper.class.getName(), "Upgrading DB");
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }



}
