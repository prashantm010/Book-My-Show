package com.example.models;

import com.example.enums.BookingStatus;
import com.example.utils.IdGenerator;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Booking {
    String id;
    MovieShow movieShow;
    User user;
    List<Seat> seatList;
    BookingStatus bookingStatus;
    Payment payment;
    LocalDateTime bookingDate;

    public Booking(MovieShow movieShow, User user, List<Seat> seatList, BookingStatus bookingStatus, Payment payment) {
        this.id = IdGenerator.getId();
        this.movieShow = movieShow;
        this.user = user;
        this.seatList = seatList;
        this.bookingStatus = bookingStatus;
        this.payment = payment;
        this.bookingDate = LocalDateTime.now();
    }

}
