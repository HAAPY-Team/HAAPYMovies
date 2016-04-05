package com.haapyindustries.haapymovies.enums;

/**
 * User Type Enum
 * Contains the possible Types of a User
 *
 * @author pjztam
 * @version M9
 */
public enum UserType {
    /**
     * User user type
     */
    USER(UserType.USERTYPE),
    /**
     * Admin user type
     */
    ADMIN(UserType.ADMINTYPE);

    /**
     * User type
     */
    private String type;

    /**
     * User user type text
     */
    private static final String USERTYPE = "User";
    /**
     * Admin user type text
     */
    private static final String ADMINTYPE = "Admin";

    /**
     * Enum Constructor
     *
     * @param typeParam in human readable format
     */
    UserType(String typeParam) {
        this.type = typeParam;
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
        if(s.equals(UserType.USERTYPE)) {
            return UserType.USER;
        } else if (s.equals(UserType.ADMINTYPE)) {
            return UserType.ADMIN;
        }
        return UserType.USER;
    }
}
