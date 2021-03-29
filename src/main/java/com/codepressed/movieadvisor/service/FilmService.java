package com.codepressed.movieadvisor.service;

import com.codepressed.movieadvisor.dao.FilmDao;
import com.codepressed.movieadvisor.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    @Autowired
    FilmDao filmDao;

    @Autowired
    FilmQueryService queryService;

    public Collection<String> findAllGenres(){
        List<String> result = null;
        result = filmDao.findAll()
                .stream()
                .map(Film::getGenres) // f -> f.getGenres()
                .flatMap(Collection::stream) // list -> list.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return result;
    }

    public Collection<Film> findAll(){
        return filmDao.findAll();
    }

    public Collection<Film> findByAnyGenre(String... genres){
        return queryService.anyGenre(genres).exec();
    }

    public Collection<Film> findByAllGenres(String... genres){
        return queryService.allGenres(genres).exec();
    }

    public Collection<Film> findByYear(String year){
        return queryService.year(year).exec();
    }

    public Collection<Film> findBetweenYears(String from, String to){
        return queryService.betweenYears(from, to).exec();
    }

    public Collection<Film> findByTitleContains(String title){
        return queryService.titleContains(title).exec();
    }

}
