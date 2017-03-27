package es.eGames.controllers;

import es.eGames.forms.GameDetailsForm;
import es.eGames.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by daniel on 1/03/17.
 */
@RestController
@RequestMapping(value = "/game")
public class DetailsGameController {

    @Autowired
    private GameService gameService;


    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<GameDetailsForm> getGameDetails(@RequestParam(required = true) String gameId, @RequestParam(required = false, defaultValue = "") String orderBy) {

        ResponseEntity responseEntity;

        try {
            GameDetailsForm game = gameService.detailsOfGame(new Integer(gameId), orderBy);
            System.out.println(orderBy);
            responseEntity = ResponseEntity.ok().body(game);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }

    @RequestMapping(value = "/customList", method = RequestMethod.GET)
    public ResponseEntity<List> customList(@RequestParam int gameId, @RequestParam(required = true) String type, @RequestHeader HttpHeaders headers) {
        ResponseEntity responseEntity;

        List<GameDetailsForm> personalGameList;
        try {
            personalGameList = gameService.listGames(gameId, type, headers);
            responseEntity = ResponseEntity.ok().body(personalGameList);
        } catch (Exception oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;
    }


}
