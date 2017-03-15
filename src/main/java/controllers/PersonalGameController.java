package controllers;

import model.PersonalGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.PersonalGameService;
import utils.HandleValidationErrors;

import javax.validation.Valid;

/**
 * Created by daniel on 15/03/17.
 */
@RequestMapping(value ="/personalgame")
@RestController
public class PersonalGameController {

    @Autowired
    private PersonalGameService personalGameService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@Valid @RequestBody PersonalGame personalGame, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, personalGame));

        } else {
            responseEntity = ResponseEntity.ok().body(personalGameService.save(personalGame));
        }
        return responseEntity;
    }

}
