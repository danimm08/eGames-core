package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;
import es.eGames.views.View;

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
public class Note extends BaseEntity {

    private String text;
    private Date date;

    private Exchange exchange;
    private User user;

    public Note() {
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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonView(View.DetailsOfPersonalGame.class)
    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonView(View.DetailsOfPersonalGame.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
