package com.example.services;

import com.example.enums.Genre;
import com.example.models.Cinema;
import com.example.models.Movie;
import com.example.models.MovieShow;
import com.example.storages.CinemaStore;
import com.example.storages.MovieStore;
import com.example.storages.ShowStore;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private MovieStore movieStore = MovieStore.getInstance();
    private CinemaStore cinemaStore = CinemaStore.getInstance();
    private ShowStore showStore = ShowStore.getInstance();

    public List<Movie> searchMovieByTitle(String title) {
        return movieStore.getAllMovies().values().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Movie> searchMovieByGenre(String genre) {
        return movieStore.getAllMovies().values().stream()
                .filter(m -> m.getGenreList().contains(Genre.valueOf(genre)))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMovieByLanguage(String lang) {
        return movieStore.getAllMovies().values().stream()
                .filter(m -> m.getLanguages().contains(lang))
                .collect(Collectors.toList());
    }

    // Search cinemas in a city
    public List<Cinema> searchCinemasByCity(String city) {
        return cinemaStore.getAllCinemas().values().stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // Search shows by movie title in a city
    public List<MovieShow> searchShows(String movieTitle, String city) {
        // Get all shows and filter by movie title and city
        return showStore.getAllShows().values().stream()
                .filter(show -> show.getCinema().getCity().equalsIgnoreCase(city))
                .filter(show -> show.getMovie().getTitle().equalsIgnoreCase(movieTitle))
                .collect(Collectors.toList());
    }
}
