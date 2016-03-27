package com.haapyindustries.haapymovies.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ratings Model
 * Contains Ratings for all Movies
 *
 * @author Yuanhan Pan
 * @version M8
 */
public class Ratings {
    private static Map<String, RatingData> RatingMap = new HashMap<>();
    private static Map<String, Integer> UserRatingMap = new HashMap<>();
    private static Map<String, MajorRatings> MajorMap = new HashMap<>();




    /**
     * Adds a Rating for a specified Movie
     *
     * @param movie Rating is For
     * @param rating for the Movie
     */
    public static void addRating(String movie, int rating) {
        String major = UserManager.getUserMajor();
        if (UserRatingMap.get(movie) == null) {
            MajorRatings majorRatings = MajorMap.get(major);
            if (majorRatings == null) {
                majorRatings = new MajorRatings(major);
            }
            majorRatings.addRating(movie, rating);
            MajorMap.put(major, majorRatings);
        } else if(UserRatingMap.get(movie) != rating) {
            MajorRatings majorRatings = MajorMap.get(major);
            if (majorRatings == null) {
                majorRatings = new MajorRatings(major);
            }
            majorRatings.updateRating(movie, rating - UserRatingMap.get(movie));
            MajorMap.put(major, majorRatings);
        }
        UserRatingMap.put(movie, rating);
    }

    /**
     * Gets the overall Rating of a Movie
     *
     * @param movie The movie you want to get the rating of
     * @return the Overall Rating of a Movie
     */
    public static RatingData getOverallRating(String movie) {
        String major = UserManager.getUserMajor();
        MajorRatings majorRatings = MajorMap.get(major);
        if (majorRatings == null) {
            return null;
        }
        return majorRatings.getRating(movie);
    }

    /**
     * Gets a Rating for a Movie
     *
     * @param movie The movie you want to get the rating of
     * @return the Rating of the Movie
     */
    public static Integer getUserRating(String movie) {
        return UserRatingMap.get(movie);
    }

    /**
     * Gets Recommendationed Movies for a given Major
     *
     * @param major The major you want recommendations for
     * @return List of recommended Movies
     */
    public static ArrayList<String> getReccommendationByMajor(String major) {
        MajorRatings majorRatings = MajorMap.get(major);
        if (majorRatings == null) {
            return null;
        }
        if (majorRatings.getTopMovieName() != null) {
            ArrayList<String> results = new ArrayList<>();
            results.add(majorRatings.getTopMovieName());
            results.add(Integer.toString(majorRatings.getTopRating()));
            return results;
        }
        return null;
    }

    /**
     * Clears a User's Ratings
     */
    public static void clearUserRatings() {
        UserRatingMap = new HashMap<>();
    }
}
