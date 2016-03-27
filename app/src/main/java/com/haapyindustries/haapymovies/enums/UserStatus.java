package com.haapyindustries.haapymovies.enums;

/**
 * User Status Enum
 * Contains the possible statuses of a User
 *
 * @author pjztam
 * @version M9
 */
public enum UserStatus {
    ACTIVE(UserStatus.active),
    LOCKED(UserStatus.locked),
    BANNED(UserStatus.banned);

    private String status;

    private static final String active = "Active";
    private static final String locked = "Locked";
    private static final String banned = "Banned";

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

    /**
     * Deserialize enum
     * @param s serialized enum
     * @return deserialized enum
     */
    public static UserStatus stringToUserStatus(String s) {
        if(s.equals(UserStatus.active)) {
            return UserStatus.ACTIVE;
        } else if (s.equals(UserStatus.locked)) {
            return UserStatus.LOCKED;
        } else if (s.equals(UserStatus.banned)) {
            return UserStatus.BANNED;
        }
        return UserStatus.ACTIVE;
    }
}
