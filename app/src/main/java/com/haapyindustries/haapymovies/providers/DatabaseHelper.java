package com.haapyindustries.haapymovies.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pjztam on 3/26/2016.
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

    private static final String DATABASE_NAME            = "haapymovies.db";
    private static final int    DATABASE_VERSION         = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseHelper.class.getName(), "Upgrading DB");
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }



}