package com.example.services;

import com.example.enums.SeatStatus;
import com.example.models.MovieShow;
import com.example.models.Seat;
import com.example.storages.ShowStore;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieShowService {
    private ShowStore movieShowStore = ShowStore.getInstance();

    public List<Seat> getSeatMap(String showId) {
        MovieShow show = movieShowStore.getShow(showId);
        if (show == null) throw new IllegalArgumentException("Show not found: " + showId);

        Map<Seat, SeatStatus> availability = show.getSeatAvailability();
        return availability.entrySet().stream()
                .map(entry -> new Seat(entry.getKey().getSeatNumber(), entry.getKey().getSeatType(), entry.getValue(), entry.getKey().getPrice()))
                .collect(Collectors.toList());
    }

    public void printSeatMap(String showId) {
        List<Seat> seats = getSeatMap(showId);
        System.out.println("Seat Map for Show: " + showId);
        for (Seat info : seats) {
            System.out.println("Seat: " + info.getSeatNumber() +
                    " | Type: " + info.getSeatType() +
                    " | Status: " + info.getSeatStatus() +
                    " | Price: " + info.getPrice());
        }
    }
}
