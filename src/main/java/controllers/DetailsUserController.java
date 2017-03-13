package controllers;

import model.PersonalGame;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

/**
 * Created by daniel on 4/03/17.
 */
@RestController
@RequestMapping(value = "/user")
public class DetailsUserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/details", method = RequestMethod.GET)

    public ResponseEntity<PersonalGame> getGameDetails(@RequestParam(required = true) String userId) {

        ResponseEntity responseEntity;

        try {
            User user = userService.findById(new Integer(userId));
            responseEntity = ResponseEntity.ok().body(user);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }


}
