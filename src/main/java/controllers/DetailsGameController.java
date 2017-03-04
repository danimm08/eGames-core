package controllers;

import forms.GameDetailsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import servicies.GameService;

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
            responseEntity = ResponseEntity.ok().body(game);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }


}
