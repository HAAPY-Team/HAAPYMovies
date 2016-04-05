package com.haapyindustries.haapymovies;

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
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest

/**
 * Test Cases for UserManager
 *
 * @author pjztam
 * @version M10
 */
public class UserManagerTest {

    protected Context context;
    protected TestDatabase db;

    protected User locked;
    protected User banned;
    protected User normal;
    protected User admin;

    @Before
    public void setupTests() {
        context = InstrumentationRegistry.getTargetContext();
        db = new TestDatabase(context);
        seedDatabase(db);
    }

    private void seedDatabase(Database db) {
        locked = new User("a", "a", "a");
        locked.setStatus(UserStatus.LOCKED);

        banned = new User("b", "b", "b");
        banned.setStatus(UserStatus.BANNED);

        normal = new User("c", "c", "c");
        admin = new User("d", "d", "d", UserType.ADMIN);

        db.addUser(locked);
        db.addUser(banned);
        db.addUser(normal);
        db.addUser(admin);
    }

    @After
    public void teardownTests() {
        db.close();
    }

    @Test
    public void wrongUsername() {
        assertNull(UserManager.handleLoginRequest("e", "a", db));
    }

    @Test
    public void accountLocked() {
        assertEquals(UserManager.handleLoginRequest("a", "a", db).getUserStatus(),UserStatus.LOCKED);
    }

    @Test
    public void accountBanned() {
        assertEquals(UserManager.handleLoginRequest("b", "b", db).getUserStatus(),UserStatus.BANNED);
    }

    @Test
    public void passwordCorrect() {
        User normal = UserManager.handleLoginRequest("c", "c", db);
        assertEquals(normal.getUserStatus(),UserStatus.ACTIVE);
        assertEquals(UserManager.getLoggedInUser().getUsername(), normal.getUsername());
    }

    @Test
    public void passwordIncorrectNotAdmin() {
        User normal = UserManager.handleLoginRequest("c", "d", db);
        assertEquals(normal.getUserStatus(),this.normal.getUserStatus());
        assertEquals(normal.getLoginTries(), 1);
    }

    @Test
    public void passwordIncorrectIsAdmin() {
        User admin = UserManager.handleLoginRequest("d", "e", db);
        assertEquals(admin.getUserStatus(),this.admin.getUserStatus());
        assertEquals(admin.getLoginTries(), 0);
    }
}
