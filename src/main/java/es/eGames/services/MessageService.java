package es.eGames.services;

import es.eGames.forms.MessageForm;
import es.eGames.model.Message;
import es.eGames.model.User;
import es.eGames.repositories.MessageRepository;
import es.eGames.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 31/03/17.
 */
@Transactional
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public MessageService() {
        super();
    }

    public List<String> getChats() {
        List<String> chats = new ArrayList<>();
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        List<Message> messages = messageRepository.findChats(principal);
        for (Message m : messages) {
            if (!m.getSender().equals(principal)) {
                chats.add(m.getSender().getUserAccount().getUsername());
            }
            if (!m.getRecipient().equals(principal)) {
                chats.add(m.getRecipient().getUserAccount().getUsername());
            }
        }
        return chats;
    }

    public List<Message> getMessagesFromChat(String username) {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        List<Message> result;
        result = messageRepository.findMessagesFromChat(principal.getUserAccount().getUsername(), username);
        return result;
    }

    public void sendMessage(MessageForm messageForm) {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Message message = new Message();
        message.setText(messageForm.getText());
        message.setRecipient(messageForm.getRecipient());
        message.setMoment(new Date());
        message.setSender(principal);
        message.setStatus(false);
        messageRepository.save(message);
    }
}
