package controllers;

import forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servicies.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Errors", bindingResult.getAllErrors());
            map.put("User", registrationForm);
            responseEntity = ResponseEntity.badRequest().body(map);

        } else {
            responseEntity = ResponseEntity.ok().body(userService.save(registrationForm));
        }
        return responseEntity;
    }

}
