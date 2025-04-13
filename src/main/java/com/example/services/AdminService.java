package com.example.services;

import com.example.enums.SeatStatus;
import com.example.enums.ShowStatus;
import com.example.models.*;
import com.example.storages.CinemaStore;
import com.example.storages.MovieStore;
import com.example.storages.ShowStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AdminService {
    private MovieStore movieStore = MovieStore.getInstance();
    private CinemaStore cinemaStore = CinemaStore.getInstance();
    private ShowStore showStore = ShowStore.getInstance();


    // Add methods for admin functionalities
    // For example, managing users, viewing reports, etc.
    public void addMovie(Movie movie) {
        if (Objects.nonNull(movieStore.getMovie(movie.getId()))) {
            throw new IllegalArgumentException("Movie already exists.");
        }
        movieStore.addMovie(movie);
    }

    public void removeMovie(String movieId) {
        if (Objects.isNull(movieStore.getMovie(movieId))) {
            throw new IllegalArgumentException("Movie does not exist.");
        }
        movieStore.removeMovie(movieId);
        System.out.println("Movie removed: " + movieId);
    }

    public void addCinema(Cinema cinema) {
        if (Objects.nonNull(cinemaStore.getCinema(cinema.getId()))) {
            throw new IllegalArgumentException("Cinema already exists.");
        }
        cinemaStore.addCinema(cinema);
    }

    public void addShow(MovieShow show) {
        if (Objects.nonNull(showStore.getShow(show.getId()))) {
            throw new IllegalArgumentException("Show already exists.");
        }

        Screen screen = show.getScreen();
        Map<Seat, SeatStatus> seatMap = new HashMap<>();
        for (Seat seat : screen.getSeatList()) {
            seatMap.put(seat, SeatStatus.AVAILABLE);
        }
        show.setSeatAvailability(seatMap);

        showStore.addShow(show);
    }

    public void removeShow(String showId) {
        if (Objects.isNull(showStore.getShow(showId))) {
            throw new IllegalArgumentException("Show does not exist.");
        }
        showStore.removeShow(showId);
        System.out.println("Show removed: " + showId);
    }

    public void cancelShow(String showId) {
        MovieShow show = showStore.getShow(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found.");
        }
        show.setShowStatus(ShowStatus.CANCELLED);
        showStore.updateShow(showId, show);
        System.out.println("Show cancelled: " + showId);
    }
}
