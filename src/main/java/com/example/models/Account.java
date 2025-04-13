package com.example.models;

import com.example.enums.UserType;
import lombok.Data;

import java.util.List;

@Data
public class Account {
    String username;
    String password;
    User user;
    UserType userType;
    List<Booking> bookings;

    public Account(String username, String password, User user, UserType userType, List<Booking> bookings) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.userType = userType;
        this.bookings = bookings;
    }
}
