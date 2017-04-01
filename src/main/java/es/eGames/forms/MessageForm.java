package es.eGames.forms;

import es.eGames.model.User;

/**
 * Created by daniel on 1/04/17.
 */
public class MessageForm {

    private String text;
    private User recipient;

    public MessageForm() {
        super();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
