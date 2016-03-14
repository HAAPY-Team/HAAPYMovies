package com.haapyindustries.haapymovies.enums;

/**
 * Created by pjztam on 3/13/2016.
 */
public enum UserStatus {
    ACTIVE("Active"),
    LOCKED("Locked"),
    BANNED("Banned");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
