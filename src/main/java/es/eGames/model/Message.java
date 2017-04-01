package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.views.View;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Message extends BaseEntity {

    private String text;
    private Date moment;
    private Boolean status;

    private User sender;
    private User recipient;

    public Message() {
        super();
    }

    @NotBlank
    @JsonView(View.DetailsOfPersonalGame.class)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NotNull
    @Past
    @JsonView(View.DetailsOfPersonalGame.class)
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @NotNull
    @JsonView(View.DetailsOfPersonalGame.class)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonView(View.DetailsOfPersonalGame.class)
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonView(View.DetailsOfPersonalGame.class)
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
