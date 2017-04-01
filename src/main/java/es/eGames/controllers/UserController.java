package es.eGames.controllers;

/**
 * Created by daniel on 30/03/17.
 */

import es.eGames.errors.HandleValidationErrors;
import es.eGames.forms.UserProfileForm;
import es.eGames.model.User;
import es.eGames.services.ImageService;
import es.eGames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<User> getDetailsOfUser(@RequestParam(required = true) String userId) {

        ResponseEntity responseEntity;

        try {
            User user = userService.findById(new Integer(userId));
            responseEntity = ResponseEntity.ok().body(user);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@Valid @RequestBody UserProfileForm userProfileForm, BindingResult bindingResult) {

        ResponseEntity responseEntity;
        if (bindingResult.hasErrors()) {
            responseEntity = ResponseEntity.badRequest().body(HandleValidationErrors.mapErros(bindingResult, userProfileForm));

        } else {
            userService.editProfile(userProfileForm);
            responseEntity = ResponseEntity.ok().build();
        }
        return responseEntity;
    }


    @RequestMapping(value = "/profile_picture", method = RequestMethod.POST)
    public ResponseEntity uploadProfilePicture(@RequestParam("image") MultipartFile image) throws Exception {
        ResponseEntity responseEntity;

        try {
            imageService.saveProfilePicture(image);
            responseEntity = ResponseEntity.ok().body("The image has been uploaded successfully");
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam String toSearch) {

        ResponseEntity responseEntity;

        try {
            List<User> games = userService.search(toSearch);
            responseEntity = ResponseEntity.ok().body(games);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }

    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public ResponseEntity followOrUnfollow(@RequestParam String username) {
        ResponseEntity responseEntity;

        try {
            userService.followOrUnfollow(username);
            responseEntity = ResponseEntity.ok().build();
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }

        return responseEntity;

    }

}
