package com.example.models;

import com.example.enums.Genre;
import com.example.utils.IdGenerator;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Movie {
    String id;
    String title;
    Integer duration;
    LocalDate releaseDate;
    String summary;
    List<Genre> genreList;
    List<String> castMembers;
    List<String> directors;
    List<String> languages;

    public Movie(String title, Integer duration, LocalDate releaseDate, String summary, List<Genre> genreList, List<String> castMembers, List<String> directors, List<String> languages) {
        this.id = IdGenerator.getId();
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.genreList = genreList;
        this.castMembers = castMembers;
        this.directors = directors;
        this.languages = languages;
    }
}
