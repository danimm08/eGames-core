package es.eGames.controllers;

import es.eGames.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by daniel on 15/03/17.
 */
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/download", method = RequestMethod.GET)
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

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity delete(@RequestParam String filename) {

        ResponseEntity responseEntity;

        try {
            imageService.deletePicture(filename);
            responseEntity = ResponseEntity.ok().build();

        } catch (Exception oops) {
            oops.printStackTrace();
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;

    }


}