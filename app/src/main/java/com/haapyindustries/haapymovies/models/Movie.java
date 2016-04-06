package com.haapyindustries.haapymovies.models;

/**
 * Movie Model
 * Contains data pertaining to Movies
 *
 * @author Aaron Andrews
 * @version M8
 */
public class Movie {

    /**
     * movieTitle of Movie
     */
    private String movieTitle;

    /**
     * Gets movieTitle of Movie
     *
     * @return Movie's movieTitle
     */
    public String getTitle() {
        return movieTitle;
    }

    /**
     * Sets movieTitle of Movie
     *
     * @param title The new title of the Movie
     */
    public void setTitle(String title) {
        movieTitle = title;
    }

    /**
     * Gets a String representation of the Movie
     * @return The String representation of the Movie
     */
    @Override
    public String toString() {
        return movieTitle;
    }
}
