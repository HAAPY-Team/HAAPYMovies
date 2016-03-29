package com.haapyindustries.haapymovies.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;
import com.haapyindustries.haapymovies.models.RatingData;
import com.haapyindustries.haapymovies.models.User;

import java.sql.SQLException;

/**
 * Database Service Provider
 * interfaces with SQLite database
 *
 * @author pjztam, Yuanhan Pan
 * @version M9
 */
public class Database {
    private SQLiteDatabase db;
    private DatabaseHelper helper;

    /**
     * Create a new Database
     *
     * @param context Current context
     */
    public Database(Context context) {
        helper = new DatabaseHelper(context);
        try {
            open();
        }
        catch (Exception e) {

        }
    }

    /**
     * Opens connection to the SQLite database
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    /**
     * Closes connection to the SQLLite database
     */
    public void close() {
        helper.close();
    }

    /**
     * Gets User from Username
     * queries db for username
     * returns associated User
     *
     * @param username of User you are searching for
     * @return User representation of the User
     */
    public User getUserFromUsername(String username) {
        Cursor c = db.query(
                helper.USER_TABLE_NAME,
                null,
                helper.USER_COLUMN_USERNAME + "=?",
                new String[] {username},
                null,
                null,
                null
        );
        c.moveToFirst();
        User user = getUserFromCursor(c);
        c.close();
        return user;
    }

    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(helper.USER_COLUMN_USERNAME,     user.getUsername());
        values.put(helper.USER_COLUMN_PASSWORD,     user.getPassword());
        values.put(helper.USER_COLUMN_MAJOR,        user.getMajor());
        values.put(helper.USER_COLUMN_USERTYPE,     user.getUserType().toString());
        values.put(helper.USER_COLUMN_USERSTATUS, user.getUserStatus().toString());
        values.put(helper.USER_COLUMN_LOGINTRIES, user.getLoginTries());
        db.update(helper.USER_TABLE_NAME, values, helper.USER_COLUMN_UID + "=?", new String[]{user.getUid() + ""});
    }

    /**
     * Gets a User from a Cursor
     * takes row in db selected by Cursor
     * turns row into a User object
     *
     * @param c Cursor pointing the row holding the User
     * @return User that is at the Cursor
     */
    private User getUserFromCursor(Cursor c) {
        if (c.getCount() == 0) {
            return null;
        }
        String username = c.getString(c.getColumnIndex(helper.USER_COLUMN_USERNAME));
        String password = c.getString(c.getColumnIndex(helper.USER_COLUMN_PASSWORD));
        String major =  c.getString(c.getColumnIndex(helper.USER_COLUMN_MAJOR));
        UserType user_type =  UserType.stringToUserType(c.getString(c.getColumnIndex(helper.USER_COLUMN_USERTYPE)));
        UserStatus user_status = UserStatus.stringToUserStatus(c.getString(c.getColumnIndex(helper.USER_COLUMN_USERSTATUS)));
        int login_tries = c.getInt(c.getColumnIndex(helper.USER_COLUMN_LOGINTRIES));
        long uid = c.getLong(c.getColumnIndex(helper.USER_COLUMN_UID));

        User user = new User(username, password, major, user_type);
        user.setStatus(user_status);
        user.setLoginTries(login_tries);
        user.setUid(uid);
        return user;
    }

    /**
     * Adds a User to the Database
     *
     * @param user to add
     */
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(helper.USER_COLUMN_USERNAME,     user.getUsername());
        values.put(helper.USER_COLUMN_PASSWORD,     user.getPassword());
        values.put(helper.USER_COLUMN_MAJOR,        user.getMajor());
        values.put(helper.USER_COLUMN_USERTYPE, user.getUserType().toString());
        values.put(helper.USER_COLUMN_USERSTATUS, user.getUserStatus().toString());
        values.put(helper.USER_COLUMN_LOGINTRIES, user.getLoginTries());
        user.setUid(db.insert(helper.USER_TABLE_NAME, null, values));
    }

    /**
     * Adds a rating to the Database
     * @param rating to add
     */
    public void addRating(RatingData rating) {
        ContentValues values = new ContentValues();
        values.put(helper.RATINGS_COLUMN_MAJOR,     rating.getMajor());
        values.put(helper.RATINGS_COLUMN_MOVIENAME,    rating.getMovie());
        values.put(helper.RATINGS_COLUMN_RATING,        rating.getRating());
        values.put(helper.RATINGS_COLUMN_USERNAME, rating.getUsername());
        rating.setRid(db.insert(helper.RATINGS_TABLE_NAME, null, values));
    }

    /**
     * Updates a rating in the database to its new values
     * @param rating to update
     */
    public void updatRating(RatingData rating) {
        ContentValues values = new ContentValues();
        values.put(helper.RATINGS_COLUMN_MAJOR,     rating.getMajor());
        values.put(helper.RATINGS_COLUMN_MOVIENAME,    rating.getMovie());
        values.put(helper.RATINGS_COLUMN_RATING,        rating.getRating());
        values.put(helper.RATINGS_COLUMN_USERNAME, rating.getUsername());
        db.update(helper.RATINGS_TABLE_NAME, values, helper.RATINGS_COLUMN_RID + "=?", new String[]{rating.getRid() + ""});
    }

    /**
     * Helper method to convert a cursor line to a RatingData instance
     * @param c the cursor
     * @return RatingData generated from the cursor
     */
    private RatingData getRatingFromCursor(Cursor c) {
        String username = c.getString(c.getColumnIndex(helper.RATINGS_COLUMN_USERNAME));
        String movieName = c.getString(c.getColumnIndex(helper.RATINGS_COLUMN_MOVIENAME));
        String major =  c.getString(c.getColumnIndex(helper.RATINGS_COLUMN_MAJOR));
        int ratingNumber = c.getInt(c.getColumnIndex(helper.RATINGS_COLUMN_RATING));
        int rid = c.getInt(c.getColumnIndex(helper.RATINGS_COLUMN_RID));
        RatingData rating = new RatingData();
        rating.setMajor(major);
        rating.setMovie(movieName);
        rating.setUsername(username);
        rating.setRating(ratingNumber);
        rating.setRid(rid);
        return rating;
    }

    /**
     * Gets the rating for a movie by the current logged in user
     * @param username the username of the current user
     * @param movie the movie to get the user's rating for
     * @return an instance of RatingData for the current user for the specified movie
     */
    public RatingData getUserRatingForMovie(String username, String movie) {
        Cursor c = db.rawQuery(
                "SELECT * FROM " + helper.RATINGS_TABLE_NAME
                        + " WHERE " + helper.RATINGS_COLUMN_USERNAME + " = ?"
                        + " AND " + helper.RATINGS_COLUMN_MOVIENAME + " = ?",
                new String[]{username, movie}
        );
        if ((c != null) && (c.getCount() > 0)) {
            c.moveToFirst();
            RatingData rating = getRatingFromCursor(c);
            c.close();
            return rating;
        }
        return null;
    }

    /**
     * Gets the overall rating for a movie for a major by calculating the average of all user submitted
     * ratings in that major
     * @param major the specified major
     * @param movie the specified movie to look up
     * @return the average rating of the specified movie from the average of all user ratings in that
     * major
     */
    public int getMajorRatingForMovie(String major, String movie) {
        Cursor c = db.rawQuery(
                "SELECT * FROM " + helper.RATINGS_TABLE_NAME
                        + " WHERE " + helper.RATINGS_COLUMN_MAJOR + " = ?"
                        + " AND " + helper.RATINGS_COLUMN_MOVIENAME + " = ?",
                new String[]{major, movie}
        );
        if ((c != null) && (c.getCount() > 0)) {
            int totalRating = 0;
            int totalUsers = c.getCount();
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                totalRating += getRatingFromCursor(c).getRating();
                c.move(1);
            }
            return totalRating / totalUsers;
        }
        return 0;
    }

    /**
     * Gets the movie with the highest overall rating for the specified major
     * @param major the major to look up
     * @return an instance of RatingData that contains the name of the movie with the highest
     * rating and its rating
     */
    public RatingData getRecommendationForMajor(String major) {
        Cursor c = db.rawQuery(
                "SELECT DISTINCT " + helper.RATINGS_COLUMN_MOVIENAME
                        + " FROM " + helper.RATINGS_TABLE_NAME
                        + " WHERE " + helper.RATINGS_COLUMN_MAJOR + " = ?",
                new String[]{major}
        );
        String[] movieNames = new String[c.getCount()];

        if (c == null || c.getCount() == 0) {
            return null;
        }
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            movieNames[i] = c.getString(c.getColumnIndex(helper.RATINGS_COLUMN_MOVIENAME));
            c.move(1);
        }

        int maxRating = 0;
        String topMovie = "";

        for (int i = 0; i < movieNames.length; i++) {
            int currentRating = getMajorRatingForMovie(major, movieNames[i]);
            if (currentRating > maxRating) {
                maxRating = currentRating;
                topMovie = movieNames[i];
            }
        }
        RatingData rating = new RatingData();
        rating.setMovie(topMovie);
        rating.setRating(maxRating);
        return rating;
    }

}
