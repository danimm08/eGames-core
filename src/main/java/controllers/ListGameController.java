package controllers;

import model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.GameService;

import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 28/02/17.
 */
@RestController
@RequestMapping("/game")
public class ListGameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listVideogames(@RequestParam(required = false, defaultValue = "") String filterBy, @RequestParam(required = false, defaultValue = "") String param) throws Exception {

        ResponseEntity responseEntity;
        Collection<Game> games;
        games = gameService.gameList(filterBy,param);

        if (games != null) {
            responseEntity = ResponseEntity.ok().body(games);
        } else {
            responseEntity = ResponseEntity.badRequest().build();
        }

        return responseEntity;
    }

}
