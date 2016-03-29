package com.haapyindustries.haapymovies.models;

import android.util.Log;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;
import com.haapyindustries.haapymovies.providers.Database;

import java.util.ArrayList;
import java.util.Arrays;
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
public class UserManager {
    private static Map<String, User> users = new HashMap<>();
    private static Set<String> majors = new HashSet<>();


    private static User user;

    /**
     * Adds a User to the Database
     *
     * @param username
     * @param password
     * @param major
     */
    public static void addUser(String username, String password, String major, Database db) {
        User user = new User(username, password, major);
       if (!doesUserExist(username, db)) {
           db.addUser(user);
       }

    }

    /**
     * Adds an Admin to the Database
     * @param username
     * @param password
     * @param major
     */
    public static void addAdmin(String username, String password, String major, Database db) {
        User user = new User(username, password, major, UserType.ADMIN);
        if (!doesUserExist(username, db)) {
            db.addUser(user);
        }
    }

    /**
     * Handles login requests
     * logs a User in if their username and password are valid
     * returns an error if credentials are invalid or user can't login
     *
     * @param username
     * @param password
     * @return Null if User doesn't exist, User otherwise
     */
    public static User handleLoginRequest(String username, String password, Database db) {
        User curr = db.getUserFromUsername(username) ;
        if (curr == null) {
            Log.d("Login Failed", "wrong username");
            return null;
        } else if (curr.getUserStatus() == UserStatus.LOCKED) {
            Log.d("Login Failed", "account locked");
            return curr;
        } else if (curr.getUserStatus() == UserStatus.BANNED) {
            Log.d("Login Failed", "account banned");
            return curr;
        } else if (curr.checkPass(password)) {
            Log.d("Login Success", "login success");
            user = curr;
            curr.setLoginTries(0);
            return curr;
        } else if (curr.getUserType() != UserType.ADMIN){
            curr.setLoginTries(curr.getLoginTries() + 1);
            Log.d("Login Failed", "Wrong Password");
            if (curr.getLoginTries() >= 3) {
                curr.setStatus(UserStatus.LOCKED);
                Log.d("Login Failed", "User is now locked");
            }
            return curr;
        } else {
            return curr;
        }
    }

    /**
     * Checks if User already exists in database
     *
     * @param username
     * @return True if user is already in DB, false otherwise
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
