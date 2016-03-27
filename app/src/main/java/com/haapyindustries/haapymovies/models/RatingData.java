package com.haapyindustries.haapymovies.models;

/**
 * RatingData Model
 * Holds the individual Rating of a Movie
 */
public class RatingData {
    public int currentRating;
    public int ratedUsers;
    private String major;
    private String username;
    private String movie;
    private int rating;
    private int rid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setRid(long rid) {
        this.rid = (int) rid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

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
