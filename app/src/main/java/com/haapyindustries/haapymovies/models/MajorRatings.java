package com.haapyindustries.haapymovies.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Yuanhan on 3/6/2016.
 */
public class MajorRatings {
    private Stack<String> topMovies;
    private Stack<Integer> topRating;
    private static Map<String, RatingData> RatingMap = new HashMap<>();

    public String major;

    public MajorRatings(String major) {
        this.major = major;
        topRating = new Stack<Integer>();
        topMovies = new Stack<String>();
    }

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

    public RatingData getRating(String movie) {
        return RatingMap.get(movie);
    }

    public String getTopMovieName() {
        return topMovies.peek();
    }

    public int getTopRating() {
        return topRating.peek();
    }

}
