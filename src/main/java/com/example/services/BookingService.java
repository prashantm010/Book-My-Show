package com.example.services;

import com.example.enums.*;
import com.example.models.*;
import com.example.storages.ShowStore;
import com.example.utils.SeatLockManager;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookingService {
    private final NotificationService notificationService;
    private final ShowStore showStore = ShowStore.getInstance();
    private final SeatLockManager seatLockManager = new SeatLockManager();

    public Booking createBooking(User user, MovieShow show, List<String> seatIds, PaymentMode paymentMode) {
        // Validate user
        if (user == null || show == null || seatIds == null || seatIds.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking details");
        }

        // Filter requested seats from the show's auditorium
        Map<String, Seat> seatMap = show.getScreen().getSeatList().stream()
                .collect(Collectors.toMap(Seat::getSeatNumber, s -> s));

        // Validate seats
        List<Seat> selectedSeats = new ArrayList<>();
        for (String seatId : seatIds) {
            Seat seat = seatMap.get(seatId);
            if (seat == null) throw new IllegalArgumentException("Seat ID not found: " + seatId);
            selectedSeats.add(seat);
        }

        seatLockManager.lockSeats(show.getId(), selectedSeats);
        try {
            for (Seat seat : selectedSeats) {
                SeatStatus status = show.getSeatAvailability().get(seat);
                if (status != SeatStatus.AVAILABLE) {
                    throw new IllegalArgumentException("Seat " + seat.getSeatNumber() + " is not available.");
                }
            }

            double totalAmount = selectedSeats.stream()
                    .mapToDouble(s -> s.getPrice() * 100) // base price = 100
                    .sum();

            // Create booking
            Payment payment = new Payment(paymentMode, totalAmount, PaymentStatus.SUCCESS);
            Booking booking = new Booking(show, user, selectedSeats, BookingStatus.CONFIRMED, payment);
            user.getAccount().getBookings().add(booking);

            // Update show with booked seats
            for (Seat seat : selectedSeats) {
                show.getSeatAvailability().put(seat, SeatStatus.BOOKED);
            }
            notificationService.notify(user, new Notification(NotificationType.BOOKING_CONFIRMED, "Your booking has been confirmed successfully."));
            return booking;
        } finally {
            seatLockManager.unlockSeats(show.getId(), selectedSeats);
        }
    }


    public void cancelBooking(User user, String bookingId) {
        Booking booking = user.getAccount().getBookings().stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            System.out.println("Booking already cancelled.");
            return;
        }

        // Free the seats
        MovieShow show = booking.getMovieShow();
        List<Seat> seatsToRelease = booking.getSeatList();
        try {
            seatLockManager.lockSeats(show.getId(), seatsToRelease);
            for (Seat seat : seatsToRelease) {
                show.getSeatAvailability().put(seat, SeatStatus.AVAILABLE);
            }
            booking.setBookingStatus(BookingStatus.CANCELLED);

            notificationService.notify(user, new Notification(NotificationType.BOOKING_CANCELLED, "Your booking has been cancelled successfully."));
            System.out.println("Booking cancelled successfully.");
        } finally {
            seatLockManager.unlockSeats(show.getId(), seatsToRelease);
        }
    }

}
