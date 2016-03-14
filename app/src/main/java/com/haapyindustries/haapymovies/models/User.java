package com.haapyindustries.haapymovies.models;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;

/**
 * User Model
 * Stores Data pertaining to Users, and holds associated methods
 *
 * @author Yuanhan Pan, pjztam
 * @version M8
 */
public class User {
    private String username;
    private String password;
    private String major;
    private UserType userType;
    private UserStatus userStatus;
    private int loginTries;

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
        this.userType = UserType.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    /**
     * Creats a new User
     *
     * @param username
     * @param password
     * @param major
     * @param userType
     */
    public User(String username, String password, String major, UserType userType) {
        this.username = username;
        this.password = password;
        this.major = major;
        this.userType = userType;
        this.userStatus = UserStatus.ACTIVE;
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
     * Gets User Type
     *
     * @return user type
     */
    public UserType getUserType() {
        return this.userType;
    }

    /**
     * Gets User Status
     *
     * @return user status
     */
    public UserStatus getUserStatus() {
        return this.userStatus;
    }

    /**
     * Sets a User's Status
     *
     * @param status of User
     */
    public void setStatus(UserStatus status) {
        this.userStatus = status;
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

    /**
     * Gets the Number of Login attempts
     *
     * @return number of login tries
     */
    public int getLoginTries() {
        return this.loginTries;
    }

    /**
     * Sets the Number of Login attempts
     *
     * @param tries The new number of Tries
     */
    public void setLoginTries(int tries) {
        this.loginTries = tries;
    }
}

