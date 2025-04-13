package com.example.storages;// datastore/CinemaStore.java
import com.example.models.Cinema;

import java.util.*;

public class CinemaStore {

    private static CinemaStore instance = null;
    private Map<String, Cinema> cinemaMap;

    private CinemaStore() {
        cinemaMap = new HashMap<>();
    }

    public static synchronized CinemaStore getInstance() {
        if (instance == null) {
            instance = new CinemaStore();
        }
        return instance;
    }

    public void addCinema(Cinema cinema) {
        cinemaMap.put(cinema.getId(), cinema);
    }

    public Cinema getCinema(String cinemaId) {
        return cinemaMap.get(cinemaId);
    }

    public Map<String, Cinema> getAllCinemas() {
        return cinemaMap;
    }
}
