package controllers;

import com.fasterxml.jackson.annotation.JsonView;
import model.PersonalGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import servicies.PersonalGameService;
import views.View;

import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 3/03/17.
 */
@RestController
@RequestMapping(value = "/personalgame")
public class DetailsPersonalGameController {

    @Autowired
    private PersonalGameService personalGameService;


    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity<PersonalGame> getGameDetails(@RequestParam(required = true) String personalGameId) {

        ResponseEntity responseEntity;

        try {
            PersonalGame personalGame = personalGameService.findById(personalGameId);
            responseEntity = ResponseEntity.ok().body(personalGame);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @JsonView(View.ListPersonalGame.class)
    public ResponseEntity<List<PersonalGame>> listPersonalGamesByUser(@RequestParam(required = true) int userId) throws Exception {

        ResponseEntity responseEntity;
        Collection<PersonalGame> personalGames;

        try {
            personalGames = personalGameService.listPersonalGamesByUser(userId);
            responseEntity = ResponseEntity.ok().body(personalGames);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;
    }



}
