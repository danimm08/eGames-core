package es.eGames.controllers;

import es.eGames.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by daniel on 31/03/17.
 */
@RequestMapping(value = "/message")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public ResponseEntity getChats() {
        ResponseEntity responseEntity;

        try {
            List<String> chats = messageService.getChats();
            responseEntity = ResponseEntity.ok().body(chats);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

}
