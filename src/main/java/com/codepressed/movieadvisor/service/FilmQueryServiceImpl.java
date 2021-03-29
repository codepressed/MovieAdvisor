package com.codepressed.movieadvisor.service;

import com.codepressed.movieadvisor.dao.FilmDao;
import com.codepressed.movieadvisor.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FilmQueryServiceImpl implements FilmQueryService{

    @Autowired //Autoinyected
    FilmDao dao;

    private Predicate<Film> predicate;
    private void init(){
        predicate = null;
    }

    @Override
    public Collection<Film> exec() {
        // @formatter:off
        return dao.findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public FilmQueryService anyGenre(String... genres) {
        Predicate<Film> pAnyGenre = (film -> Arrays.stream(genres).anyMatch(film.getGenres()::contains));
        predicate = (predicate == null) ? pAnyGenre : predicate.and(pAnyGenre);
        return this;
    }

    @Override
    public FilmQueryService allGenres(String... genres) {
        Predicate<Film> pAnyGenre = (film -> Arrays.stream(genres).allMatch(film.getGenres()::contains));
        predicate = (predicate == null) ? pAnyGenre : predicate.and(pAnyGenre);
        return this;
    }

    @Override
    public FilmQueryService year(String year) {
        Predicate<Film> pYear = (film -> film.getYear().equalsIgnoreCase(year));
        predicate = (predicate == null) ? pYear : predicate.and(pYear);
        return this;
    }

    @Override
    public FilmQueryService betweenYears(String from, String to) {
        Predicate<Film> pBetweenYears = (film -> {
            LocalDate fromYear = LocalDate.of(Integer.parseInt(from), 1, 1);
            LocalDate toYear = LocalDate.of(Integer.parseInt(to), 1, 3);
            LocalDate filmYear = LocalDate.of(Integer.parseInt(film.getYear()), 1, 2); //If it's in the middle, it's included
            return filmYear.isAfter(fromYear) && filmYear.isBefore(toYear);
        });
        predicate = (predicate == null) ? pBetweenYears : predicate.and(pBetweenYears);
        return this;
    }

    @Override
    public FilmQueryService titleContains(String title) {
        Predicate<Film> pTitleContains = (film -> film.getTitle().toLowerCase().contains(title.toLowerCase()));
        predicate = (predicate == null) ? pTitleContains : predicate.and(pTitleContains);
        return this;
    }
}
