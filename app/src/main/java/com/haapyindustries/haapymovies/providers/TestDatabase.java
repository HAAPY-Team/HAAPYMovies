package com.haapyindustries.haapymovies.providers;

import android.content.Context;

/**
 * Created by pjztam on 4/4/2016.
 */
public class TestDatabase extends Database {

    private Context context;

    private static final String TEST_DATABASE_NAME = "haapymovies.test.db";
    /**
     * Create a new Database
     *
     * @param context Current context
     */
    public TestDatabase(Context context) {
        super(context, TEST_DATABASE_NAME);
        this.context = context;
    }

    @Override
    public void close() {
        helper.close();
        context.deleteDatabase(TEST_DATABASE_NAME);
    }
}
