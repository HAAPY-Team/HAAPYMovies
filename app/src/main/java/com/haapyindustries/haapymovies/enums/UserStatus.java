package com.haapyindustries.haapymovies.enums;

/**
 * User Status Enum
 * Contains the possible statuses of a User
 *
 * @author pjztam
 * @version M8
 */
public enum UserStatus {
    ACTIVE("Active"),
    LOCKED("Locked"),
    BANNED("Banned");

    private String status;

    /**
     * Enum Constructor
     *
     * @param status in human readable format
     */
    UserStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the Status as a String
     *
     * @return a human readable String representation of the Status
     */
    @Override
    public String toString() {
        return this.status;
    }
}
