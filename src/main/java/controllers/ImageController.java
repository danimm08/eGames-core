package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import services.ImageService;

/**
 * Created by daniel on 15/03/17.
 */
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "user/profile_picture", method = RequestMethod.POST)
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile image) throws Exception {
        ResponseEntity responseEntity;

        try {
            imageService.saveProfilePicture(image);
            responseEntity = ResponseEntity.ok().body("The image has been uploaded successfully");
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

}