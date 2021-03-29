package com.codepressed.movieadvisor;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class MovieAdvisorHelp {
    private String help;

    @PostConstruct
    public void init(){
        try{
            help = Files
                    .lines(Paths.get(ResourceUtils.getFile("classpath:help.txt").toURI()))
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.err.println("ERROR: Text file is missing");
            System.exit(-1);
            e.printStackTrace();
        }
    }
    public String getHelp(){
        return help;
    }
}
