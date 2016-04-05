package com.haapyindustries.haapymovies;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.haapyindustries.haapymovies.enums.UserType;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest

/**
 * Created by Adrian Rodriguez-Febres on 4/4/2016.
 * Test class for testing StringToUserType method.
 */
public class StringToUserTypeTest{

    @Test
    public void stringToUserTypeShouldReturnUserForInvalid() {
        assertEquals(UserType.USER, UserType.stringToUserType(new String()));
        assertEquals(UserType.USER, UserType.stringToUserType("I am Invalid."));
    }

    @Test
    public void stringToUserTypeShouldReturnAdminForAdmin() {
        assertEquals(UserType.ADMIN, UserType.stringToUserType("Admin"));
    }

    @Test
    public void stringToUserTypeShouldReturnUserForUser() {
        assertEquals(UserType.USER, UserType.stringToUserType("User"));
    }

}
