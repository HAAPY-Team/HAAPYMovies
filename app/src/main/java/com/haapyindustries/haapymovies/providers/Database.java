package com.haapyindustries.haapymovies.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;
import com.haapyindustries.haapymovies.models.User;

import java.sql.SQLException;

/**
 * Created by pjztam on 3/26/2016.
 */
public class Database {
    private SQLiteDatabase db;
    private DatabaseHelper helper;

    public Database(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public User getUserFromUsername(String username) {
        Cursor c = db.query(DatabaseHelper.USER_TABLE_NAME, null, DatabaseHelper.USER_COLUMN_USERNAME + "=?", new String[] {username}, null, null, null);
        c.moveToFirst();
        User user = getUserFromCursor(c);
        c.close();
        return user;
    }

    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_COLUMN_USERNAME,     user.getUsername());
        values.put(DatabaseHelper.USER_COLUMN_PASSWORD,     user.getPassword());
        values.put(DatabaseHelper.USER_COLUMN_MAJOR,        user.getMajor());
        values.put(DatabaseHelper.USER_COLUMN_USERTYPE,     user.getUserType().toString());
        values.put(DatabaseHelper.USER_COLUMN_USERSTATUS, user.getUserStatus().toString());
        values.put(DatabaseHelper.USER_COLUMN_LOGINTRIES, user.getLoginTries());
        db.update(DatabaseHelper.USER_TABLE_NAME, values, DatabaseHelper.USER_COLUMN_UID + "=?", new String[] {user.getUid() + ""});
    }

    private User getUserFromCursor(Cursor c) {
        String username = c.getString(c.getColumnIndex(DatabaseHelper.USER_COLUMN_USERNAME));
        String password = c.getString(c.getColumnIndex(DatabaseHelper.USER_COLUMN_PASSWORD));
        String major =  c.getString(c.getColumnIndex(DatabaseHelper.USER_COLUMN_MAJOR));
        UserType user_type =  UserType.stringToUserType(c.getString(c.getColumnIndex(DatabaseHelper.USER_COLUMN_USERTYPE)));
        UserStatus user_status = UserStatus.stringToUserStatus(c.getString(c.getColumnIndex(DatabaseHelper.USER_COLUMN_USERSTATUS)));
        int login_tries = c.getInt(c.getColumnIndex(DatabaseHelper.USER_COLUMN_LOGINTRIES));
        long uid = c.getLong(c.getColumnIndex(DatabaseHelper.USER_COLUMN_UID));

        User user = new User(username, password, major, user_type);
        user.setStatus(user_status);
        user.setLoginTries(login_tries);
        user.setUid(uid);
        return user;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_COLUMN_USERNAME,     user.getUsername());
        values.put(DatabaseHelper.USER_COLUMN_PASSWORD,     user.getPassword());
        values.put(DatabaseHelper.USER_COLUMN_MAJOR,        user.getMajor());
        values.put(DatabaseHelper.USER_COLUMN_USERTYPE,     user.getUserType().toString());
        values.put(DatabaseHelper.USER_COLUMN_USERSTATUS, user.getUserStatus().toString());
        values.put(DatabaseHelper.USER_COLUMN_LOGINTRIES, user.getLoginTries());
        user.setUid(db.insert(DatabaseHelper.USER_TABLE_NAME, null, values));
    }

}
