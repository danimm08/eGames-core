package es.eGames.controllers;

import es.eGames.errors.HandleValidationErrors;
import es.eGames.forms.RegistrationForm;
import es.eGames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by daniel on 10/02/17.
 */
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap> registration(@Valid @RequestBody RegistrationForm registrationForm, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        try{
            if (bindingResult.hasErrors()) {
                responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, registrationForm));

            } else {
                userService.register(registrationForm);
                responseEntity = ResponseEntity.ok().build();
            }
        }catch(DataIntegrityViolationException e){
            LinkedHashMap<String,List> errors = new LinkedHashMap<>();
            List<String> auxList = new ArrayList<>();
            auxList.add("That username is not available");
            errors.put("username", auxList);
            responseEntity = ResponseEntity.badRequest().body(errors);
        }

        return responseEntity;
    }



}
