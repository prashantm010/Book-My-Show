package com.example.models;

import com.example.utils.IdGenerator;
import lombok.Data;

import java.util.List;

@Data
public class Cinema {
    String id;
    String name;
    String city;
    List<Screen> screens;

    public Cinema(String name, String city, List<Screen> screens) {
        this.id = IdGenerator.getId();
        this.name = name;
        this.city = city;
        this.screens = screens;
    }
}
