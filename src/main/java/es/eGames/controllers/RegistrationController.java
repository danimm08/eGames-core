package es.eGames.controllers;

import es.eGames.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import es.eGames.services.UserService;
import es.eGames.utils.HandleValidationErrors;

import javax.validation.Valid;

/**
 * Created by daniel on 10/02/17.
 */
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Object> registration(@Valid @RequestBody RegistrationForm registrationForm, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, registrationForm));

        } else {
            responseEntity = ResponseEntity.ok().body(userService.save(registrationForm));
        }
        return responseEntity;
    }



}
