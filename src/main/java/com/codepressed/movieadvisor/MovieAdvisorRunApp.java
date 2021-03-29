package com.codepressed.movieadvisor;

import com.codepressed.movieadvisor.config.AppConfig;
import com.codepressed.movieadvisor.model.Film;
import com.codepressed.movieadvisor.service.FilmQueryService;
import com.codepressed.movieadvisor.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieAdvisorRunApp {
    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmQueryService filmQueryService;
    @Autowired
    private MovieAdvisorHelp help;

    public void run(String[] args){
        if (args.length < 1){
            System.out.println("ERROR: Syntax error");
            System.out.println(help.getHelp());
        } else if(args.length == 1){
            switch (args[0].toLowerCase()){
                case "-lg":
                    filmService.findAllGenres().forEach(System.out::println);
                    break;
                case "-h":
                    System.out.println(help.getHelp());
                    break;
                default:
                    System.out.println("ERROR: Syntax line error");
                    System.out.println(help.getHelp());
            }
        } else if(args.length % 2 != 0){
            System.out.println("ERROR: Syntax param, missing arg");
            System.out.println(help.getHelp());
        } else if(args.length > 8){
            System.out.println("ERROR: Syntax error, arg limit");
            System.out.println(help.getHelp());
        } else{
            List<String[]> arguments = new ArrayList<>();
            for (int i = 0; i < args.length; i+=2){
                arguments.add(new String[]{args[i], args[i+1]});
            }
            boolean error = false;
            for(String[] argument : arguments){
                switch (argument[0].toLowerCase()){
                    case "-ag":
                        filmQueryService.anyGenre(argument[1].split(","));
                        break;
                    case "-tg":
                        filmQueryService.allGenres(argument[1].split(","));
                        break;
                    case "-y":
                        filmQueryService.year(argument[1]);
                        break;
                    case "-b":
                        String[] years = argument[1].split(",");
                        filmQueryService.betweenYears(years[0], years[1]);
                        break;
                    case "-t":
                        filmQueryService.titleContains(argument[1]);
                        break;
                    default:
                        error = true;
                        System.out.println("ERROR: Syntax error, ¿Wrong char?");

                }
            }
            if(!error){
                Collection<Film> result = filmQueryService.exec();
                System.out.printf("%s\t%-50s\t%s\t%s\n","ID","title","year","genres");
                if(result != null){
                    result.forEach(f -> System.out.printf("%s\t%-50s\t%s\t%s\n",
                            f.getId(), f.getTitle(), f.getYear(), f.getGenres().stream().collect(Collectors.joining(", "))));
                }else{
                    System.out.println("No results.");
                }
            }
        }
    }
}
