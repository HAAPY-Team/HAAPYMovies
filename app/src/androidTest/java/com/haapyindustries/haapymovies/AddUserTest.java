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
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
/**
 * Created by Aaron on 4/4/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class AddUserTest {
    protected Context context;
    protected TestDatabase db;


    @Before
    public void setupTests() {
        context = InstrumentationRegistry.getTargetContext();
        db = new TestDatabase(context);

    }

    @Test
    public void testAddedUserIsInDatabase() throws Exception {
        UserManager.addUser("aaron", "pass", "cs", db);
        assertEquals("aaron", db.getUserFromUsername("aaron").getUsername());
        assertEquals("pass", db.getUserFromUsername("aaron").getPassword());
        assertEquals("cs", db.getUserFromUsername("aaron").getMajor());


    }

    @Test
    public void testAddedAdminIsInDatabase() throws Exception {
        UserManager.addAdmin("patrick", "pass", "cs", db);
        assertEquals("patrick", db.getUserFromUsername("patrick").getUsername());
        assertEquals("pass", db.getUserFromUsername("patrick").getPassword());
        assertEquals("cs", db.getUserFromUsername("patrick").getMajor());
        assertEquals(UserType.ADMIN.toString(), db.getUserFromUsername("patrick").getUserType().toString());
    }



}