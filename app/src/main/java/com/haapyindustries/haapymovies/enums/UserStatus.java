package com.haapyindustries.haapymovies.enums;

/**
 * User Status Enum
 * Contains the possible statuses of a User
 *
 * @author pjztam
 * @version M9
 */
public enum UserStatus {
    /**
     * Active status
     */
    ACTIVE(UserStatus.ACTIVESTATUS),
    /**
     * Locked status
     */
    LOCKED(UserStatus.LOCKEDSTATUS),
    /**
     * Banned status
     */
    BANNED(UserStatus.BANNEDSTATUS);

    /**
     * Status of User
     */
    private String status;

    /**
     * Active Status Text
     */
    private static final String ACTIVESTATUS = "Active";
    /**
     * Locked Status Text
     */
    private static final String LOCKEDSTATUS = "Locked";
    /**
     * Banned Status Text
     */
    private static final String BANNEDSTATUS = "Banned";

    /**
     * Enum Constructor
     *
     * @param statusParam in human readable format
     */
    UserStatus(String statusParam) {
        this.status = statusParam;
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

    /**
     * Deserialize enum
     * @param s serialized enum
     * @return deserialized enum
     */
    public static UserStatus stringToUserStatus(String s) {
        if (s != null) {
            if (s.equals(UserStatus.ACTIVESTATUS)) {
                return UserStatus.ACTIVE;
            } else if (s.equals(UserStatus.LOCKEDSTATUS)) {
                return UserStatus.LOCKED;
            } else if (s.equals(UserStatus.BANNEDSTATUS)) {
                return UserStatus.BANNED;
            }
        }
        return UserStatus.ACTIVE;
    }
}
