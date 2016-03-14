package com.haapyindustries.haapymovies.models;

/**
 * RatingData Model
 * Holds the individual Rating of a Movie
 */
public class RatingData {
    public int currentRating;
    public int ratedUsers;

    /**
     * Constructs an Empty RatingData Object
     */
    public RatingData() {

    }

    /**
     * Constructs a RatingData Object for a given User with a given Rating
     * @param rating of a Movie
     * @param users Rating belongs to
     */
    public RatingData(int rating, int users) {
        currentRating = rating;
        ratedUsers = users;
    }
}
