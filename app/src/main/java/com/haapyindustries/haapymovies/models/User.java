package com.haapyindustries.haapymovies.models;

/**
 * User Model
 * Stores Data pertaining to Users, and holds associated methods
 *
 * @author Yuanhan Pan, pjztam
 * @version M4
 */
public class User {
    private String username;
    private String password;
    private String major;

    /**
     * Creates a new User
     *
     * @param username
     * @param password
     * @param major
     */
    public User(String username, String password, String major) {
        this.username = username;
        this.password = password;
        this.major = major;
    }

    /**
     * Checks if password if correct for this User
     *
     * @param password
     * @return True if password is correct, False otherwise
     */
    public boolean checkPass(String password) {
        return this.password.equals(password);
    }

    /**
     * Gets Username
     *
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets Password
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets Major
     *
     * @return major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * Sets Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets Major
     *
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }
}
