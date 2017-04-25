package es.eGames.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.errors.HandleValidationErrors;
import es.eGames.forms.PersonalGameForm;
import es.eGames.model.PersonalGame;
import es.eGames.services.ImageService;
import es.eGames.services.PersonalGameService;
import es.eGames.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 15/03/17.
 */
@RequestMapping(value = "/personalgame")
@RestController
public class PersonalGameController {

    @Autowired
    private PersonalGameService personalGameService;

    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@Valid @RequestBody PersonalGameForm personalGame, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, personalGame));

        } else {
            personalGameService.savePersonalGameForm(personalGame);
            responseEntity = ResponseEntity.ok().build();
        }
        return responseEntity;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<Object> edit(@RequestParam int personalGameId, @Valid @RequestBody PersonalGameForm personalGameForm, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, personalGameForm));

        } else {
            personalGameService.editPersonalGame(personalGameId, personalGameForm);
            responseEntity = ResponseEntity.ok().build();
        }
        return responseEntity;
    }

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
    @JsonView(View.DetailsOfPersonalGame.class)
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadPersonalGamePicture(@RequestParam("image") MultipartFile image, @RequestParam String personalGameId) throws Exception {
        ResponseEntity responseEntity;

        try {
            imageService.savePersonalGamePicture(image, personalGameId);
            responseEntity = ResponseEntity.ok().body("The image has been uploaded successfully");
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity deletePersonalGame(@RequestParam int personalGameId) {
        ResponseEntity responseEntity;

        try {
            personalGameService.delete(personalGameId);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity search(@RequestParam String toSearch) {

        ResponseEntity responseEntity;

        try {
            List<PersonalGame> games = personalGameService.search(toSearch);
            responseEntity = ResponseEntity.ok().body(games);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }
}
