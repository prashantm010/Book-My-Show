package com.example.models;

import com.example.enums.SeatStatus;
import com.example.enums.ShowStatus;
import com.example.utils.IdGenerator;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class MovieShow {
    String id;
    Movie movie;
    Cinema cinema;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Screen screen;
    ShowStatus showStatus;
    Map<Seat, SeatStatus> seatAvailability;

    public MovieShow(Movie movie, Cinema cinema, Screen screen, LocalDateTime startTime, LocalDateTime endTime, ShowStatus showStatus) {
        this.id = IdGenerator.getId();
        this.movie = movie;
        this.cinema = cinema;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showStatus = showStatus;
        this.seatAvailability = generateSeatMap(screen);
    }

    public Boolean isSeatAvailable(Seat seat) {
        return seatAvailability.get(seat) == SeatStatus.AVAILABLE;
    }

    private Map<Seat, SeatStatus> generateSeatMap(Screen screen) {
        Map<Seat, SeatStatus> seatStatusMap = new HashMap<>();
        for (Seat seat: screen.getSeatList()) {
            seatStatusMap.put(seat, SeatStatus.AVAILABLE);
        }
        return seatStatusMap;
    }
}
