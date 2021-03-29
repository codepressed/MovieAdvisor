package com.codepressed.movieadvisor;

import com.codepressed.movieadvisor.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MovieAdvisorApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MovieAdvisorRunApp runApp = appContext.getBean(MovieAdvisorRunApp.class);
        runApp.run(args);
        appContext.close();
        //SpringApplication.run(MovieAdvisorApplication.class, args);
    }

}
