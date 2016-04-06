package com.haapyindustries.haapymovies.models;


import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.enums.UserType;
import com.haapyindustries.haapymovies.models.User;
import com.haapyindustries.haapymovies.models.UserManager;
import com.haapyindustries.haapymovies.providers.Database;
import com.haapyindustries.haapymovies.providers.TestDatabase;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;


import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;
import org.junit.runner.RunWith;

import java.lang.Exception;
import java.util.List;

import dalvik.annotation.TestTargetClass;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
/**
 * Created by Henry on 4/4/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class AddUserNotInDatabaseTest {
    protected Context context;
    protected TestDatabase db;


    @Before
    public void setupTests() {
        context = InstrumentationRegistry.getTargetContext();
        db = new TestDatabase(context);

    }

    @Test
    public void testAddedUserIsNotInDatabase() throws Exception {
        // in the case that the user is not in the db already, just add the user.
        UserManager.addUser("henry", "pass", "cs", db);
        assertEquals("henry", db.getUserFromUsername("henry").getUsername());
        assertEquals("pass", db.getUserFromUsername("henry").getPassword());
        assertEquals("cs", db.getUserFromUsername("henry").getMajor());
        assertEquals(UserType.USER.toString(), db.getUserFromUsername("henry").getUserType().toString());

    }

    @Test
    public void testAddedAdminIsNotInDatabase() throws Exception {
        // in the case that the admin is not in the db already, just add the admin.
        UserManager.addAdmin("patrick", "pass", "cs", db);
        assertEquals("patrick", db.getUserFromUsername("patrick").getUsername());
        assertEquals("pass", db.getUserFromUsername("patrick").getPassword());
        assertEquals("cs", db.getUserFromUsername("patrick").getMajor());
        assertEquals(UserType.ADMIN.toString(), db.getUserFromUsername("patrick").getUserType().toString());
    }

    @Test
    public void testUserNoOverwrite() {
        // in the case that the user is already in the database
        UserManager.addUser("billy", "pass", "cs", db);
        UserManager.addUser("billy", "password", "compSci", db);
        //tests to ensure that new user with an existing username does not overwrite an existing one
        assertEquals("billy", db.getUserFromUsername("billy").getUsername());
        assertEquals("pass", db.getUserFromUsername("billy").getPassword());
        assertEquals("cs", db.getUserFromUsername("billy").getMajor());
        assertEquals(UserType.USER.toString(), db.getUserFromUsername("billy").getUserType().toString());
    }

    @Test
    public void testAdminNoOverwrite() {
        // in the case that the admin is already in the database
        UserManager.addAdmin("steven", "password", "cs", db);
        UserManager.addAdmin("steven", "123mypassword", "compSci", db);
        //tests to ensure that new user with an existing username does not overwrite an existing one
        assertEquals("steven", db.getUserFromUsername("steven").getUsername());
        assertEquals("password", db.getUserFromUsername("steven").getPassword());
        assertEquals("cs", db.getUserFromUsername("steven").getMajor());
        assertEquals(UserType.ADMIN.toString(), db.getUserFromUsername("steven").getUserType().toString());
    }

}