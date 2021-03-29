package com.codepressed.movieadvisor.dao;

import com.codepressed.movieadvisor.config.AppConfig;
import com.codepressed.movieadvisor.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmDaoImplMemory implements FilmDao{
    public List<Film> movies = new ArrayList<>();

    @Autowired
    private AppConfig appConfig;

    @PostConstruct
    public void init(){
        movies = UtilFilmFileReader.readFile(appConfig.getFile(), appConfig.getSeparator(), appConfig.getListSeparator());

    }
    @Override
    public Film findById(long id) {

        Optional<Film> result = movies.stream().filter(f -> f.getId() == id).findFirst();
        return result.orElse(null);
    }

    @Override
    public Collection<Film> findAll() {
        return movies;
    }

    @Override
    public void insert(Film film) {
        movies.add(film);
    }

    @Override
    public void edit(Film film) {

    }

    @Override
    public void delete(long id) {
        int index = getIndexOf(id);
        if (index != -1)
            movies.remove(index);

    }

    private int getIndexOf(long id){
        boolean found = false;
        int index = 0;
        while(!found && index < movies.size()){
            if(movies.get(index).getId() == id)
                found = true;
            else
                index++;
        }
        return (found) ? index: -1; //If found is true, return index. Else, return -1
    }
}
