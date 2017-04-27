package es.eGames.controllers;

import es.eGames.forms.QualificationForm;
import es.eGames.model.Qualification;
import es.eGames.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by daniel on 3/04/17.
 */
@RestController
@RequestMapping(value = "/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity uploadPersonalGamePicture(@RequestBody QualificationForm qualificationForm, @RequestParam Integer exchangeId) throws Exception {
        ResponseEntity responseEntity;

        try {
            qualificationService.save(qualificationForm, exchangeId);
            responseEntity = ResponseEntity.ok().build();
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(required = true) int userId) throws Exception {

        ResponseEntity responseEntity;
        Collection<Qualification> qualifications;

        try {
            qualifications = qualificationService.findByUserId(userId);
            responseEntity = ResponseEntity.ok().body(qualifications);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;
    }

    @RequestMapping(value = "/checkIsAllowedToQualify", method = RequestMethod.GET)
    public ResponseEntity isAllowedToQualify(@RequestParam(required = true) int exchangeId) throws Exception {

        ResponseEntity responseEntity;
        Boolean res;
        try {
            res = qualificationService.isAllowedToQualify(exchangeId);
            responseEntity = ResponseEntity.ok().body(res);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;
    }

}
