package controllers;

import com.fasterxml.jackson.annotation.JsonView;
import forms.ExchangeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.ExchangeService;
import utils.HandleValidationErrors;
import views.View;

import javax.validation.Valid;

/**
 * Created by daniel on 9/03/17.
 */
@RestController
@RequestMapping(value = "exchange/")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity createExchange(@RequestParam int personalGameId) {

        ResponseEntity responseEntity;

        try {
            ExchangeForm ef = exchangeService.createForm(personalGameId);
            responseEntity = ResponseEntity.ok().body(ef);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@Valid @RequestBody ExchangeForm exchangeForm, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, exchangeForm));

        } else {
            responseEntity = ResponseEntity.ok().body(exchangeService.save(exchangeForm));
        }
        return responseEntity;
    }

}
