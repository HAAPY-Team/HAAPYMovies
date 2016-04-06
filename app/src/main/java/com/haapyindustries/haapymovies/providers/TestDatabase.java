package com.haapyindustries.haapymovies.providers;

import android.content.Context;

/**
 * Created by pjztam on 4/4/2016.
 */
public class TestDatabase extends Database {

    /**
     * The activity context
     */
    private Context context;

    /**
     * The name of the test database
     */
    private static final String TEST_DATABASE_NAME = "haapymovies.test.db";
    /**
     * Create a new Database
     *
     * @param contextParam Current context
     */
    public TestDatabase(Context contextParam) {
        super(contextParam, TEST_DATABASE_NAME);
        this.context = contextParam;
    }

    @Override
    public void close() {
        getHelper().close();
        context.deleteDatabase(TEST_DATABASE_NAME);
    }
}
