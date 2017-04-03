package es.eGames.controllers;

import es.eGames.forms.GameDetailsForm;
import es.eGames.model.Game;
import es.eGames.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 1/03/17.
 */
@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;


    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<GameDetailsForm> getGameDetails(@RequestParam(required = true) String gameId, @RequestParam(required = false, defaultValue = "") String orderBy) {

        ResponseEntity responseEntity;

        try {
            GameDetailsForm game = gameService.detailsOfGame(new Integer(gameId), orderBy);
            responseEntity = ResponseEntity.ok().body(game);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }

    @RequestMapping(value = "/customList", method = RequestMethod.GET)
    public ResponseEntity<List> customList(@RequestParam(required = false) Integer gameId, @RequestParam(required = true) String type) {
        ResponseEntity responseEntity;

        List<GameDetailsForm> personalGameList;
        try {
            personalGameList = gameService.listGames(gameId, type);
            responseEntity = ResponseEntity.ok().body(personalGameList);
        } catch (Exception oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listVideogames(@RequestParam(required = false, defaultValue = "") String filterBy, @RequestParam(required = false, defaultValue = "") String param, @RequestParam(defaultValue = "1") Integer page) throws Exception {

        ResponseEntity responseEntity;
        Collection<Game> games;
        games = gameService.gameList(filterBy,param, page);

        if (games != null) {
            responseEntity = ResponseEntity.ok().body(games);
        } else {
            responseEntity = ResponseEntity.badRequest().build();
        }

        return responseEntity;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam String toSearch) {

        ResponseEntity responseEntity;

        try {
            List<Game> games = gameService.search(toSearch);
            responseEntity = ResponseEntity.ok().body(games);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }


}
