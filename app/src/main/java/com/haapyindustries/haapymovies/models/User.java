package com.haapyindustries.haapymovies.models;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;

/**
 * User Model
 * Stores Data pertaining to Users, and holds associated methods
 *
 * @author Yuanhan Pan, pjztam
 * @version M9
 */
public class User {
    /**
     * Id of User
     */
    private long uid;
    /**
     * Username of User
     */
    private String username;
    /**
     * Password of User
     */
    private String password;
    /**
     * Major of User
     */
    private String major;
    /**
     * Type of User
     */
    private UserType userType;
    /**
     * Status of User
     */
    private UserStatus userStatus;
    /**
     * Login tries of User
     */
    private int loginTries;

    /**
     * Creates a new User
     *
     * @param usernameParam username of the User
     * @param passwordParam password of the User
     * @param majorParam major of the User
     */
    public User(String usernameParam, String passwordParam, String majorParam) {
        this.uid = -1;
        this.username = usernameParam;
        this.password = passwordParam;
        this.major = majorParam;
        this.userType = UserType.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    /**
     * Creats a new User
     *
     * @param usernameParam username of the User
     * @param passwordParam password of the User
     * @param majorParam major of the User
     * @param userTypeParam user type of the User
     */
    public User(String usernameParam, String passwordParam, String majorParam, UserType userTypeParam) {
        this.uid = -1;
        this.username = usernameParam;
        this.password = passwordParam;
        this.major = majorParam;
        this.userType = userTypeParam;
        this.userStatus = UserStatus.ACTIVE;
    }

    /**
     * Checks if password if correct for this User
     *
     * @param passwordParam password of the User
     * @return True if password is correct, False otherwise
     */
    public boolean checkPass(String passwordParam) {
        return this.password.equals(passwordParam);
    }

    /**
     * Gets Username
     *
     * @return username username of the User
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets Password
     *
     * @return password password of the User
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets Major
     *
     * @return major major of the User
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
     * @param statusParam of User
     */
    public void setStatus(UserStatus statusParam) {
        this.userStatus = statusParam;
    }
    /**
     * Sets Password
     *
     * @param passwordParam password of the User
     */
    public void setPassword(String passwordParam) {
        this.password = passwordParam;
    }

    /**
     * Sets Major
     *
     * @param majorParam of User
     */
    public void setMajor(String majorParam) {
        this.major = majorParam;
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
     * @param triesParam The new number of Tries
     */
    public void setLoginTries(int triesParam) {
        this.loginTries = triesParam;
    }

    /**
     * Gets the User's UID
     * @return User's UID
     */
    public long getUid() {
        return this.uid;
    }

    /**
     * Sets the User's UID
     * @param uidParam User's UID
     */
    public void setUid(Long uidParam) {
        this.uid = uidParam;
    }
}

