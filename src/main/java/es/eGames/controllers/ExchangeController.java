package es.eGames.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.errors.HandleValidationErrors;
import es.eGames.forms.DetailsOfExchangeForm;
import es.eGames.forms.ExchangeForm;
import es.eGames.services.ExchangeService;
import es.eGames.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by daniel on 9/03/17.
 */
@RestController
@RequestMapping(value = "/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
        try {
            if (bindingResult.hasErrors()) {
                responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, exchangeForm));

            } else {
                responseEntity = ResponseEntity.ok().body(exchangeService.save(exchangeForm));
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @RequestMapping(value = "/accept", method = RequestMethod.GET)
    public ResponseEntity accept(@RequestParam int exchangeId) {
        ResponseEntity responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(exchangeService.acceptOrDecline(exchangeId, true));
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;

    }

    @RequestMapping(value = "/decline", method = RequestMethod.GET)
    public ResponseEntity decline(@RequestParam int exchangeId) {
        ResponseEntity responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(exchangeService.acceptOrDecline(exchangeId, false));
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;

    }

    @RequestMapping(value = "/getExchangeInfo", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity getExchangeInfo(@RequestParam int exchangeId) {

        ResponseEntity responseEntity;

        try {
            DetailsOfExchangeForm ef = exchangeService.getExchangeInfo(exchangeId);
            responseEntity = ResponseEntity.ok().body(ef);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/negotiate", method = RequestMethod.POST)
    public ResponseEntity<Object> negotiate(@RequestParam int exchangeId, @Valid @RequestBody ExchangeForm exchangeForm, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        try {
            if (bindingResult.hasErrors()) {
                responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, exchangeForm));
            } else {
                responseEntity = ResponseEntity.ok().body(exchangeService.negotiate(exchangeForm, exchangeId));
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getMyExchanges", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity getListOfMyExchanges() {

        ResponseEntity responseEntity;

        try {
            List<DetailsOfExchangeForm> myExchanges = exchangeService.getListOfMyExchanges();
            responseEntity = ResponseEntity.ok().body(myExchanges);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getExchangeFormInfo", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity getExchangeFormInfo(@RequestParam int exchangeId) {

        ResponseEntity responseEntity;

        try {
            ExchangeForm ef = exchangeService.getExchangeFormInfo(exchangeId);
            responseEntity = ResponseEntity.ok().body(ef);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/createNegotiation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity createNegotiation(@RequestParam int exchangeId) {

        ResponseEntity responseEntity;

        try {
            ExchangeForm ef = exchangeService.createNegotiation(exchangeId);
            responseEntity = ResponseEntity.ok().body(ef);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

}
