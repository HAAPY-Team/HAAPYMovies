package com.haapyindustries.haapymovies.models;

/**
 * Movie Model
 * Contains data pertaining to Movies
 *
 * @author Aaron Andrews
 * @version M8
 */
public class Movie {
    private String Title;
    private String Date;

    /**
     * Gets Title of Movie
     *
     * @return Movie's Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Sets Title of Movie
     *
     * @param title The new title of the Movie
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Gets a String representation of the Movie
     * @return The String representation of the Movie
     */
    @Override
    public String toString() {
        return Title;
    }
}
