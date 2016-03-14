package com.haapyindustries.haapymovies.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Major Ratings Model
 * Contains and Handles Movie Ratings
 *
 * @author Yuanhan Pan
 * @version M8
 */
public class MajorRatings {
    private Stack<String> topMovies;
    private Stack<Integer> topRating;
    private static Map<String, RatingData> RatingMap = new HashMap<>();

    public String major;

    /**
     * Constructor
     * Initializes instance variables
     *
     * @param major Major that these ratings are for
     */
    public MajorRatings(String major) {
        this.major = major;
        topRating = new Stack<Integer>();
        topMovies = new Stack<String>();
    }

    /**
     * Adds a Rating to the Database
     *
     * @param movie Rating is For
     * @param rating for a Movie
     */
    public void addRating(String movie, int rating) {
        RatingData data = new RatingData();
        if (RatingMap.get(movie) == null) {
            data.currentRating = rating;
            data.ratedUsers = 1;

        } else {
            RatingData currentRating = RatingMap.get(movie);
            int currentUsers = currentRating.ratedUsers + 1;
            int totalRating = currentRating.currentRating * currentRating.ratedUsers + rating;
            data.currentRating = totalRating / currentUsers;
            data.ratedUsers = currentUsers;
        }
        RatingMap.put(movie, data);

        if (RatingMap.size() == 1) {
            topRating.push(data.currentRating);
            topMovies.push(movie);
        } else if (movie.equals(topMovies.peek())) {
            if (data.currentRating < topRating.peek() && topRating.size() > 1) {
                topMovies.pop();
                topRating.pop();
                if (topRating.isEmpty() || data.currentRating > topRating.peek()) {
                    topMovies.push(movie);
                    topRating.push(data.currentRating);
                }
            } else if (data.currentRating > topRating.peek()) {
                topRating.pop();
                topRating.push(data.currentRating);
            }
        } else if (data.currentRating > topRating.peek()) {
            topMovies.push(movie);
            topRating.push(data.currentRating);
        }
    }

    /**
     * Updates an existing movie's rating
     *
     * @param movie Rating is for
     * @param rating ratinf of the Movie
     */
    public void updateRating(String movie, int rating) {
        RatingData data = new RatingData();
        if (RatingMap.get(movie) == null) {
            data.currentRating = rating;
            data.ratedUsers = 1;

        } else {
            RatingData currentRating = RatingMap.get(movie);
            int currentUsers = currentRating.ratedUsers;
            int totalRating = currentRating.currentRating * currentRating.ratedUsers + rating;
            data.currentRating = totalRating / currentUsers;
            data.ratedUsers = currentUsers;
        }
        RatingMap.put(movie, data);

        if (RatingMap.size() == 1) {
            topRating.push(data.currentRating);
            topMovies.push(movie);
        } else if (movie.equals(topMovies.peek())) {
            if (data.currentRating < topRating.peek() && topRating.size() > 1) {
                topMovies.pop();
                topRating.pop();
                if (topRating.isEmpty() || data.currentRating > topRating.peek()) {
                    topMovies.push(movie);
                    topRating.push(data.currentRating);
                }
            } else if (data.currentRating > topRating.peek()) {
            topRating.pop();
            topRating.push(data.currentRating);
            }
        } else if (data.currentRating > topRating.peek()) {
            topMovies.push(movie);
            topRating.push(data.currentRating);
        }
    }

    /**
     * Gets Rating of the Movie
     *
     * @param movie Rating is for
     * @return movie's Rating
     */
    public RatingData getRating(String movie) {
        return RatingMap.get(movie);
    }

    /**
     * Gets the Top Rated Movie
     *
     * @return name of the Top Rated movie
     */
    public String getTopMovieName() {
        return topMovies.peek();
    }

    /**
     * Gets the Top Rating
     *
     * @return rating of the Top Rated movie
     */
    public int getTopRating() {
        return topRating.peek();
    }

}
