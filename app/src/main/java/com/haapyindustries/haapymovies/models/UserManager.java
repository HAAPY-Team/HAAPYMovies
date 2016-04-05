package com.haapyindustries.haapymovies.models;

import android.util.Log;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;
import com.haapyindustries.haapymovies.providers.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Manages Users
 * Holds Users and manipulates Users
 *
 * @author Yuanhan Pan, pjztam
 * @version M8
 */
public final class UserManager {

    UserManager() {

    }

    /**
     * Map of all users in use
     */
    private static Map<String, User> users = new HashMap<>();
    /**
     * Set of all majors in use
     */
    private static Set<String> majors = new HashSet<>();

    /**
     * User in the manager
     */
    private static User user;

    /**
     * Adds a User to the Database
     *
     * @param username username of the User
     * @param password password of the User
     * @param major major of the User
     * @param db database context to query User
     */
    public static void addUser(String username, String password, String major, Database db){
        final User dbUser = new User(username, password, major);
        if (!doesUserExist(username, db)) {
            db.addUser(dbUser);
        }

    }

    /**
     * Adds an Admin to the Database
     * @param username of User
     * @param password of User
     * @param major of User
     * @param db database context to query User
     */
    public static void addAdmin(String username, String password, String major, Database db) {
        final User dbUser = new User(username, password, major, UserType.ADMIN);
        if (!doesUserExist(username, db)) {
            db.addUser(dbUser);
        }
    }

    /**
     * Handles login requests
     * logs a User in if their username and password are valid
     * returns an error if credentials are invalid or user can't login
     *
     * @param username of User
     * @param password of User
     * @param db database context to query User
     * @return Null if User doesn't exist, User otherwise
     */
    public static User handleLoginRequest(String username, String password, Database db) {
        final int maxLoginTries = 3;
        final String failure = "Login Failed";
        final User curr = db.getUserFromUsername(username) ;
        if (curr == null) {
            Log.d(failure, "wrong username");
            return null;
        } else if (curr.getUserStatus() == UserStatus.LOCKED) {
            Log.d(failure, "account locked");
            return curr;
        } else if (curr.getUserStatus() == UserStatus.BANNED) {
            Log.d(failure, "account banned");
            return curr;
        } else if (curr.checkPass(password)) {
            Log.d("Login Success", "login success");
            user = curr;
            curr.setLoginTries(0);
            return curr;
        } else if (curr.getUserType() != UserType.ADMIN){
            curr.setLoginTries(curr.getLoginTries() + 1);
            Log.d(failure, "Wrong Password");
            if (curr.getLoginTries() >= maxLoginTries) {
                curr.setStatus(UserStatus.LOCKED);
                Log.d(failure, "User is now locked");
            }
            return curr;
        } else {
            return curr;
        }
    }

    /**
     * Checks if User already exists in database
     *
     * @param username of User
     * @return True if user is already in DB, false otherwise
     * @param db database context to query User
     */
    public static boolean doesUserExist(String username, Database db) {


        return !(db.getUserFromUsername(username) == null);
    }

    /**
     * Returns the current logged in User
     *
     * @return user
     */
    public static User getLoggedInUser() {
        return user;
    }

    /**
     * Gets all Users
     *
     * @return a List of all Users
     */
    public static List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Logs a User out
     */
    public static void logoutUser() {
        user = null;
    }

    /**
     * Gets all Majors
     *
     * @return a List of all Majors
     */
    public static String[] getMajors() {
        return majors.toArray(new String[majors.size()]);
    }

    /**
     * Adds a Major to the List
     *
     * @param major to add
     */
    public static void addMajor(String major) {
        majors.add(major);
    }

    /**
     * Gets the current logged in User's Major
     *
     * @return current User's major
     */
    public static String getUserMajor() {
        return user.getMajor();
    }

    /**
     * Gets a User
     *
     * @param username of User to get
     * @return User
     */
    public static User getUser(String username) {
        return users.get(username);
    }
}
