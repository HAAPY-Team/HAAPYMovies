package com.haapyindustries.haapymovies.models;

/**
 * Created by Yuanhan on 2/7/2016.
 */
public class User {
    private String password;
    private String username;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkPass(String password) {
        return this.password.equals(password);
    }
}
