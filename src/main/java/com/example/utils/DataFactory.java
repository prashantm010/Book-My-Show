package com.example.utils;// utils/DataFactory.java

import com.example.enums.*;
import com.example.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DataFactory {

    public static List<Seat> generateSeats(int count) {
        List<Seat> seats = new ArrayList<>();
        int reclinerSeat = Math.ceilDiv(count, 10);
        int premiumSeat = Math.ceilDiv(count, 4);
        for (int i = 1; i <= reclinerSeat; i++) {
            seats.add(new Seat("R" + i, SeatType.RECLINER, SeatStatus.AVAILABLE, 799.0));
        }
        for (int i = 1; i <= premiumSeat; i++) {
            seats.add(new Seat("P" + i, SeatType.PREMIUM, SeatStatus.AVAILABLE, 499.0));
        }
        for (int i = 1; i <= count - reclinerSeat - premiumSeat; i++) {
            seats.add(new Seat("A" + i, SeatType.REGULAR, SeatStatus.AVAILABLE, 249.0));
        }
        return seats;
    }

    public static Screen generateScreen(String name, Integer numberOfSeats) {
        List<Seat> seats = generateSeats(numberOfSeats);
        return new Screen(name, seats);
    }

    public static Cinema generateCinema(String name, String city, Integer numberOfScreens, Integer numberOfSeats) {
        List<Screen> screens = new ArrayList<>();
        for (int i = 1; i <= numberOfScreens; i++) {
            screens.add(generateScreen("AUDI-" + i, numberOfSeats));
        }
        return new Cinema(name, city, screens);
    }

    public static Movie generateMovie(String title, Integer duration, LocalDate releaseDate, String summary, Genre genre) {
        return new Movie(title, duration, releaseDate, summary, List.of(genre), new ArrayList<>(), new ArrayList<>(), List.of("English", "Hindi"));
    }

    public static MovieShow generateShow(Movie movie, Cinema cinema, Screen screen, LocalDateTime startDateTime) {
        LocalDateTime endDateTime = startDateTime.plusMinutes(movie.getDuration());
        return new MovieShow(movie, cinema, screen, startDateTime, endDateTime, ShowStatus.SCHEDULED);
    }

    public static List<MovieShow> generateShows(Movie movie, Cinema cinema, LocalDate endDate) {
        List<MovieShow> shows = new ArrayList<>();
        LocalDateTime startTime = LocalDateTime.of(movie.getReleaseDate().getYear(), movie.getReleaseDate().getMonth(), movie.getReleaseDate().getDayOfMonth(), 10, 0);
        LocalDateTime endTime = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), 22, 0);

        for (LocalDateTime dateTime = startTime; dateTime.isBefore(endTime); dateTime = dateTime.plusHours(3)) {
            for (Screen screen : cinema.getScreens()) {
                shows.add(generateShow(movie, cinema, screen, dateTime));
            }
        }
        return shows;
    }

    public static User generateUser(String username, String name, String email, String phone) {
        User user = new User(name, email, phone, List.of(CommunicationChannel.EMAIL));
        Account account = new Account(username, "password123", user, UserType.CUSTOMER, new ArrayList<>());
        user.setAccount(account);
        return user;
    }
}
