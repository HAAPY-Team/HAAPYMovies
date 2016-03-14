package com.haapyindustries.haapymovies.enums;

/**
 * Created by pjztam on 3/13/2016.
 */
public enum UserType {
    USER("User"),
    ADMIN("Admin");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
