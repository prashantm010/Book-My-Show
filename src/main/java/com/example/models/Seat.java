package com.example.models;

import com.example.enums.SeatStatus;
import com.example.enums.SeatType;
import com.example.utils.IdGenerator;
import lombok.Data;

@Data
public class Seat {
    String id;
    String seatNumber;
    SeatType seatType;
    SeatStatus seatStatus;
    Double price;

    public Seat(String seatNumber, SeatType seatType, SeatStatus seatStatus, Double price) {
        this.id = IdGenerator.getId();
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.price = price;
    }
}
