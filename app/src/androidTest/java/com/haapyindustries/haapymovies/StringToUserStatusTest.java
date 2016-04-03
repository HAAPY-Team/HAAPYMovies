package com.haapyindustries.haapymovies;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.models.User;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by Yuanhan on 4/3/2016.
 * Test class for testing stringToUserStatus method
 */
public class StringToUserStatusTest extends TestCase{
    protected String active = "Active";
    protected String locked = "Locked";
    protected String banned = "Banned";


    protected void stringToUserStatusShouldReturnActiveForNullOrEmptyString() {
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus(new String()));
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus(""));
    }

    protected void stringToUserStatusShouldReturnCorrectStatusBasedOnInputString() {
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus(active));
        assertEquals(UserStatus.LOCKED, UserStatus.stringToUserStatus(locked));
        assertEquals(UserStatus.BANNED, UserStatus.stringToUserStatus(banned));
    }

    protected void stringToUserStatusShoudReturnActiveForIncorrectInputs() {
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus("aefwe"));
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus("  Active "));
    }
}
