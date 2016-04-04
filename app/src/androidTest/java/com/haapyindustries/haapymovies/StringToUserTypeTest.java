package com.haapyindustries.haapymovies;

import com.haapyindustries.haapymovies.enums.UserType;

import junit.framework.TestCase;
import junit.framework.Assert;

/**
 * Created by Adrian Rodriguez-Febres on 4/4/2016.
 * Test class for testing StringToUserType method.
 */
public class StringToUserTypeTest extends TestCase {

    protected void stringToUserTypeShouldReturnUserForInvalid() {
        assertEquals(UserType.USER, UserType.stringToUserType(new String()));
        assertEquals(UserType.USER, UserType.stringToUserType("I am Invalid."));
    }

    protected void stringToUserTypeShouldReturnAdminForAdmin() {
        assertEquals(UserType.ADMIN, UserType.stringToUserType("Admin"));
    }

    protected void stringToUserTypeShouldReturnUserForUser() {
        assertEquals(UserType.USER, UserType.stringToUserType("User"));
    }

}
