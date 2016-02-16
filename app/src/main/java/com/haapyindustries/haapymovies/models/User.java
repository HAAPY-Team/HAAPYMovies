package com.haapyindustries.haapymovies.models;

/**
 * Created by Yuanhan on 2/7/2016.
 */
public class User {
    private String username;
    private String password;
    private String major;

    public User(String username, String password, String major) {
        this.username = username;
        this.password = password;
        this.major = major;
    }

    public boolean checkPass(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getMajor() {
        return this.major;
    }

    public void setPassword(String b) {
        this.password = b;
    }

    public void setMajor(String b) {
        this.major = b;
    }
}
