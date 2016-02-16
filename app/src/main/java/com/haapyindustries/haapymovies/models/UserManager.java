package com.haapyindustries.haapymovies.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages Users
 * Holds Users and manipulates Users
 *
 * @author Yuanhan Pan, pjztam
 * @version M4
 */
public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    private static User user;

    /**
     * Adds a User to the Database
     *
     * @param username
     * @param password
     * @param major
     */
    public static void addUser(String username, String password, String major) {
        User user = new User(username, password, major);
        users.put(username, user);
    }

    /**
     * Handles login requests
     * logs a User in if their username and password are valid
     * returns false if that is not the case
     *
     * @param username
     * @param password
     * @return True if user was logged in, False otherwise
     */
    public static boolean handleLoginRequest(String username, String password) {
        User curr = users.get(username);
        //is username valid
        if (curr == null) {
            return false;
        //is password correct for user
        } else if (curr.checkPass(password)) {
            user = curr;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if User already exists in database
     *
     * @param username
     * @return True if user is already in DB, false otherwise
     */
    public static boolean doesUserExist(String username) {
        return !(users.get(username) == null);
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
     * Logs a User out
     */
    public static void logoutUser() {
        user = null;
    }

}
