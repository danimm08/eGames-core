package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/images/download", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity serveFile(@RequestParam String filename) {
        ResponseEntity responseEntity;
        Resource file;

        try {
            file = imageService.loadAsResource(filename);
            responseEntity =
                    ResponseEntity
                            .ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                            .contentType(imageService.getContentType(filename))
                            .body(file);

        } catch (Exception oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "personalgame/upload", method = RequestMethod.POST)
    public ResponseEntity uploadPersonalGamePicture(@RequestParam("image") MultipartFile image, @RequestParam String personalGameId) throws Exception {
        ResponseEntity responseEntity;

        try {
            imageService.savePersonalGamePicture(image, personalGameId);
            responseEntity = ResponseEntity.ok().body("The image has been uploaded successfully");
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }


}