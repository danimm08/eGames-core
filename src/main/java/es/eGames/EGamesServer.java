package es.eGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EGamesServer {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EGamesServer.class, args);
    }
}