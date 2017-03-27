package es.eGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EGamesServer {

    public static void main(String[] args) throws IOException {
        Runtime run = Runtime.getRuntime();
//        run.exec("/usr/bin/python2.7 /home/daniel/Proyectos/eGamesRecommender/manage.py runserver 8000");
        SpringApplication.run(EGamesServer.class, args);
    }
}