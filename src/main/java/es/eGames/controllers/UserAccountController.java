package es.eGames.controllers;

import es.eGames.errors.HandleValidationErrors;
import es.eGames.forms.UserUserAccountForm;
import es.eGames.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel on 30/03/17.
 */
@RestController
@RequestMapping(value = "/userAccount")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<Object> registration(@Valid @RequestBody UserUserAccountForm userUserAccountForm, BindingResult bindingResult, HttpServletRequest request) {

        ResponseEntity responseEntity;
        try {
            if (bindingResult.hasErrors()) {
                responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, userUserAccountForm));

            } else {
                Boolean auxRes = userAccountService.editUserAccount(userUserAccountForm, request);
                Map<String,Boolean> res = new HashMap();
                res.put("result", auxRes);
                responseEntity = ResponseEntity.ok().body(res);
            }
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;
    }


}