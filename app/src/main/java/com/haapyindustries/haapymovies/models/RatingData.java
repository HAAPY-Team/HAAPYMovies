package com.haapyindustries.haapymovies.models;

/**
 * @author Yuanhan
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

    /**
     * Gets the rid of a RatingData
     * @return the rid of the RatingData
     */
    public int getRid() {
        return rid;
    }

    /**
     * Sets the rid of a rating data
     * @param rid the new rid for the RatingData
     */
    public void setRid(int rid) {
        this.rid = rid;
    }

    /**
     * Sets the rid of a rating data
     * @param rid a long that is converted to an int to be set to the rid
     */
    public void setRid(long rid) {
        this.rid = (int) rid;
    }

    /**
     * Gets the major of the RatingData
     * @return the major of the RatingData
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the major of the RatingData
     * @param major the major to set the RatingData to
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Gets the username of the RatingData
     * @return the username of the RatingData
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the RatingData
     * @param username the username to set the RatingData to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the movie name of the RatingData
     * @return the movie name for the RatingData
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Sets the movie name for the RatingData
     * @param movie the movie name to set the RatingData to
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }

    /**
     * Gets the rating for the RatingData
     * @return the rating number for the RatingData
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of the RatingData
     * @param rating the rating to set the RatingData to
     */
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
