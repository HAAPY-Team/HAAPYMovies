package com.haapyindustries.haapymovies.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuanhan on 3/6/2016.
 */
public class Ratings {
    private static Map<String, RatingData> RatingMap = new HashMap<>();
    private static Map<String, Integer> UserRatingMap = new HashMap<>();
    private static Map<String, MajorRatings> MajorMap = new HashMap<>();

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

    public static RatingData getOverallRating(String movie) {
        String major = UserManager.getUserMajor();
        MajorRatings majorRatings = MajorMap.get(major);
        if (majorRatings == null) {
            return null;
        }
        return majorRatings.getRating(movie);
    }

    public static Integer getUserRating(String movie) {
        return UserRatingMap.get(movie);
    }

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

    public static void clearUserRatings() {
        UserRatingMap = new HashMap<>();
    }
}
