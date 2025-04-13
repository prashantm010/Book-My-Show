package com.example.storages;// datastore/ShowStore.java
import com.example.models.MovieShow;

import java.util.*;

public class ShowStore {

    private static ShowStore instance = null;
    private Map<String, MovieShow> showMap;

    private ShowStore() {
        showMap = new HashMap<>();
    }

    public static synchronized ShowStore getInstance() {
        if (instance == null) {
            instance = new ShowStore();
        }
        return instance;
    }

    public void addShow(MovieShow show) {
        showMap.put(show.getId(), show);
    }

    public void removeShow(String showId) {
        showMap.remove(showId);
    }

    public void updateShow(String showId, MovieShow show) {
        showMap.put(showId, show);
    }

    public MovieShow getShow(String showId) {
        return showMap.get(showId);
    }

    public Map<String, MovieShow> getAllShows() {
        return showMap;
    }
}
