package es.eGames.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.forms.MessageForm;
import es.eGames.model.Message;
import es.eGames.services.MessageService;
import es.eGames.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    @JsonView(View.DetailsOfPersonalGame.class)
    public ResponseEntity getMessagesFromChat(@RequestParam String username) {
        ResponseEntity responseEntity;

        try {
            List<Message> chats = messageService.getMessagesFromChat(username);
            responseEntity = ResponseEntity.ok().body(chats);
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody MessageForm messageForm) {
        ResponseEntity responseEntity;

        try {
            messageService.sendMessage(messageForm);
            responseEntity = ResponseEntity.ok().build();
        } catch (IllegalArgumentException oops) {
            responseEntity = ResponseEntity.badRequest().body(oops.getMessage());
        }
        return responseEntity;
    }


}
