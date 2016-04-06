package com.haapyindustries.haapymovies.models;

/**
 * @author Yuanhan
 * RatingData Model
 * Holds the individual Rating of a Movie
 */
public class RatingData {
    /**
     * Major of user who rated
     */
    private String major;
    /**
     * Username of user who rated
     */
    private String username;
    /**
     * Movie name who rated
     */
    private String movie;
    /**
     * Rating number
     */
    private int rating;
    /**
     * Rating id
     */
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
     * @param ridParam the new rid for the RatingData
     */
    public void setRid(int ridParam) {
        this.rid = ridParam;
    }

    /**
     * Sets the rid of a rating data
     * @param ridParam a long that is converted to an int to be set to the rid
     */
    public void setRid(long ridParam) {
        this.rid = (int) ridParam;
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
     * @param majorParam the major to set the RatingData to
     */
    public void setMajor(String majorParam) {
        this.major = majorParam;
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
     * @param usernameParam the username to set the RatingData to
     */
    public void setUsername(String usernameParam) {
        this.username = usernameParam;
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
     * @param movieParam the movie name to set the RatingData to
     */
    public void setMovie(String movieParam) {
        this.movie = movieParam;
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
     * @param ratingParam the rating to set the RatingData to
     */
    public void setRating(int ratingParam) {
        this.rating = ratingParam;
    }

    /**
     * Constructs an Empty RatingData Object
     */
    public RatingData() {

    }

}
