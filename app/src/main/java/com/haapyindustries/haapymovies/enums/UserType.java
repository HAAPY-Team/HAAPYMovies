package com.haapyindustries.haapymovies.enums;

/**
 * User Type Enum
 * Contains the possible Types of a User
 *
 * @author pjztam
 * @version M9
 */
public enum UserType {
    USER(UserType.user),
    ADMIN(UserType.admin);

    private String type;

    private static final String user = "User";
    private static final String admin = "Admin";

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

    /**
     * Deserialize enum
     * @param s Serialized enum
     * @return deserialized enum
     */
    public static UserType stringToUserType(String s) {
        if(s.equals(UserType.user)) {
            return UserType.USER;
        } else if (s.equals(UserType.admin)) {
            return UserType.ADMIN;
        }
        return UserType.USER;
    }
}
