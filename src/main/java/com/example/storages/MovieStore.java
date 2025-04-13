package com.example.storages;// datastore/MovieStore.java
import com.example.models.Movie;

import java.util.*;

public class MovieStore {

    private static MovieStore instance = null;
    private Map<String, Movie> movieMap;

    private MovieStore() {
        movieMap = new HashMap<>();
    }

    public static synchronized MovieStore getInstance() {
        if (instance == null) {
            instance = new MovieStore();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.getId(), movie);
    }

    public void removeMovie(String movieId) {
        movieMap.remove(movieId);
    }

    public Movie getMovie(String movieId) {
        return movieMap.get(movieId);
    }

    public Map<String, Movie> getAllMovies() {
        return movieMap;
    }
}
