package com.haapyindustries.haapymovies;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.haapyindustries.haapymovies.enums.UserStatus;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest

/**
 * Created by Yuanhan on 4/3/2016.
 * Test class for testing stringToUserStatus method
 */
public class StringToUserStatusTest {
    protected String active = "Active";
    protected String locked = "Locked";
    protected String banned = "Banned";


    @Test
    public void stringToUserStatusShouldReturnActiveForNullOrEmptyString() {
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus(new String()));
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus(""));
    }

    @Test
    public void stringToUserStatusShouldReturnCorrectStatusBasedOnInputString() {
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus(active));
        assertEquals(UserStatus.LOCKED, UserStatus.stringToUserStatus(locked));
        assertEquals(UserStatus.BANNED, UserStatus.stringToUserStatus(banned));
    }

    @Test
    public void stringToUserStatusShoudReturnActiveForIncorrectInputs() {
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus("aefwe"));
        assertEquals(UserStatus.ACTIVE, UserStatus.stringToUserStatus("  Active "));
    }
}
