package com.haapyindustries.haapymovies.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuanhan on 2/7/2016.
 */
public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    public UserManager() {

    }

    public void addUser(String username, String password, String major) {
        User user = new User(username, password, major);
        users.put(username, user);
    }

    public boolean handleLoginRequest(String username, String password) {
        User currUser = users.get(username);
        if (currUser == null) {
            return false;
        }
        return currUser.checkPass(password);
    }

    public boolean doesUserExist(String username) {
        return !(users.get(username) == null);
    }


}
