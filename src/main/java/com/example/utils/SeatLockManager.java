package com.example.utils;

import com.example.models.Seat;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class SeatLockManager {

    private final Map<String, Map<Seat, ReentrantLock>> showSeatLocks = new ConcurrentHashMap<>();

    public void lockSeats(String showId, List<Seat> seats) {
        seats.sort(Comparator.comparing(Seat::getId)); // avoid deadlocks via consistent order
        Map<Seat, ReentrantLock> seatLocks = showSeatLocks.computeIfAbsent(showId, k -> new ConcurrentHashMap<>());

        for (Seat seat : seats) {
            ReentrantLock lock = seatLocks.computeIfAbsent(seat, s -> new ReentrantLock());
            lock.lock(); // blocking
        }
    }

    public void unlockSeats(String showId, List<Seat> seats) {
        Map<Seat, ReentrantLock> seatLocks = showSeatLocks.get(showId);
        if (seatLocks == null) return;

        for (Seat seat : seats) {
            ReentrantLock lock = seatLocks.get(seat);
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
