package com.example;

import com.example.enums.Genre;
import com.example.enums.PaymentMode;
import com.example.models.*;
import com.example.services.*;
import com.example.utils.DataFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        AdminService adminService = new AdminService();
        AuthService authService = new AuthService();
        NotificationService notificationService = new NotificationService();
        BookingService bookingService = new BookingService(notificationService);
        SearchService searchService = new SearchService();
        MovieShowService seatSelectionService = new MovieShowService();

        // Register movie and cinema
        Movie movie = DataFactory.generateMovie("Jaat", 150, LocalDate.of(2025, 4, 10), "A mind-bending thriller", Genre.ACTION);
        Cinema cinema = DataFactory.generateCinema("Brookfield PVR", "Bangalore", 5, 45);
        List<MovieShow> shows = DataFactory.generateShows(movie, cinema, LocalDate.of(2025, 4, 11));

        adminService.addMovie(movie);
        adminService.addCinema(cinema);
        for (MovieShow show : shows) {
            adminService.addShow(show);
        }

        // User account creation
        User user1 = DataFactory.generateUser("Alice00", "Alice", "alice@example.com", "1234567890");
        User user2 = DataFactory.generateUser("Bob00", "Bob", "Bob@example.com", "1234567891");

        authService.register(user1);
        authService.register(user2);

        System.out.println("\n📽️ Movies available:");
        searchService.searchMovieByTitle("Ja").forEach(m -> System.out.println(m.getTitle()));

        System.out.println("\n🏢 Cinemas showing 'Jaat':");
        List<MovieShow> movieShows = searchService.searchShows("Jaat", "Bangalore");

        if (!movieShows.isEmpty()) {
            MovieShow show = movieShows.get(0);
            System.out.println("\n🎟️ Available seats for Show Jaat:");
            seatSelectionService.printSeatMap(show.getId());

            // Booking flow for Alice
            System.out.println("\n✅ Booking for Alice:");
            bookingService.createBooking(user1, show, Arrays.asList("A8", "A9", "R1", "R2", "P1"), PaymentMode.UPI);

            // Booking flow for Bob (attempting overlapping seat)
            System.out.println("\n❌ Booking for Bob (one seat already booked):");
            try {
                bookingService.createBooking(user2, show, Arrays.asList("P1", "P2", "P3"), PaymentMode.CREDIT_CARD);
            } catch (Exception e) {
                System.out.println("❗ Booking failed: " + e.getMessage());
            }

            // Cancel Alice's booking
            System.out.println("\n🔄 Cancelling Alice's booking:");
            Booking aliceBooking = user1.getAccount().getBookings().get(0);
            bookingService.cancelBooking(user1, aliceBooking.getId());

            // Try booking again for Bob now that A2 is free
            System.out.println("\n✅ Re-attempt booking for Bob:");
            bookingService.createBooking(user2, show, Arrays.asList("P1", "P2", "P3"), PaymentMode.CREDIT_CARD);

            System.out.println("\n🎟️ Available seats for Show Jaat:");
            seatSelectionService.printSeatMap(show.getId());
        }
    }
}