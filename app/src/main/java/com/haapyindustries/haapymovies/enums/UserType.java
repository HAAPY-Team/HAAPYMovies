package com.haapyindustries.haapymovies.enums;

/**
 * User Type Enum
 * Contains the possible Types of a User
 *
 * @author pjztam
 * @version M8
 */
public enum UserType {
    USER("User"),
    ADMIN("Admin");

    private String type;

    /**
     * Enum Constructor
     *
     * @param type in human readable format
     */
    UserType(String type) {
        this.type = type;
    }

    /**
     * Returns the Status as a String
     *
     * @return a human readable String representation of the Status
     */
    @Override
    public String toString() {
        return this.type;
    }
}
