package com.haapyindustries.haapymovies.models;

/**
 * Created by Yuanhan on 3/6/2016.
 */
public class RatingData {
    public int currentRating;
    public int ratedUsers;

    public RatingData() {

    }

    public RatingData(int rating, int users) {
        currentRating = rating;
        ratedUsers = users;
    }
}
