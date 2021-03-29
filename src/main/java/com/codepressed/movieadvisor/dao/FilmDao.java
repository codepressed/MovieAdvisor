package com.codepressed.movieadvisor.dao;

import com.codepressed.movieadvisor.model.Film;

import java.util.Collection;

public interface FilmDao {
    public Film findById(long id);
    public Collection<Film> findAll();
    public void insert(Film film);
    public void edit(Film film);
    public void delete(long id);
}
