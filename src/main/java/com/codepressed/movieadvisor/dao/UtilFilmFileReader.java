package com.codepressed.movieadvisor.dao;

import com.codepressed.movieadvisor.model.Film;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UtilFilmFileReader {
    public static List<Film> readFile(final String path, final String separator, final String listSeparator){
        List<Film> result = new ArrayList<>();
        try {
            result = Files.lines(Paths.get(ResourceUtils.getFile(path).toURI()))
                .skip(1) //Ignore first line. CSV HEADER
                .map(line -> {
                    String[] values = line.split(separator);
                    return new Film(Long.parseLong(values[0]), values[1], values[2],
                            Arrays.asList(values[3].split(listSeparator))); //Split comma,
                }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
