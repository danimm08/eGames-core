package controllers;

import org.jboss.logging.annotations.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import servicies.ImageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by daniel on 19/02/17.
 */
@RestController
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="user/profile_picture/{userId}", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("image") MultipartFile image, @PathVariable(value="userId") String userId) throws Exception {
        System.out.println(image.getName());

        imageService.store(image);

        return "test";
    }

}
