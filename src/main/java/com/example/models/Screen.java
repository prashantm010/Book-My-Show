package com.example.models;

import com.example.utils.IdGenerator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Screen {
    String id;
    String name;
    Cinema cinema;
    List<Seat> seatList;
    List<MovieShow> movieShows;

    public Screen(String screenNumber, Cinema cinema, List<Seat> seatList, List<MovieShow> movieShows) {
        this.id = IdGenerator.getId();
        this.name = screenNumber;
        this.cinema = cinema;
        this.seatList = seatList;
        this.movieShows = movieShows;
    }

    public Screen(String screenNumber, List<Seat> seatList) {
        this.id = IdGenerator.getId();
        this.name = screenNumber;
        this.seatList = seatList;
        this.movieShows = new ArrayList<>();
    }
}
