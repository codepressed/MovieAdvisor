package com.codepressed.movieadvisor.service;

import com.codepressed.movieadvisor.model.Film;

import java.util.Collection;

public interface FilmQueryService {
    public Collection<Film> exec();
    public FilmQueryService anyGenre(String... genres); //Three Dots = Variable arguments
    public FilmQueryService allGenres(String ...genres);
    public FilmQueryService year(String year);
    public FilmQueryService betweenYears(String from, String to);
    public FilmQueryService titleContains(String title);
}
