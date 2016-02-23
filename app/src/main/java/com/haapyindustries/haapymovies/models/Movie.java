package com.haapyindustries.haapymovies.models;

/**
 * Created by Aaron on 2/22/2016.
 */
public class Movie {
    private String Title;
    private String Date;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return Title;
    }
}
