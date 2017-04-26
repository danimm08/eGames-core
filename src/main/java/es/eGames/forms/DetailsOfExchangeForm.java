package es.eGames.forms;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.model.*;
import es.eGames.views.View;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by daniel on 31/03/17.
 */
public class DetailsOfExchangeForm {
    private Date creationDate;
    private Date lastUpdateDate;
    private Boolean status;
    private Date eventDate;
    private Integer numberOfAttemps;
    private Type type;
    private String wayExchange;

    private User user;
    private User lastModifier;
    private Set<PersonalGame> personalGameUser1;
    private Set<PersonalGame> personalGameUser2;
    private List<Note> notes;

    public DetailsOfExchangeForm(Exchange exchange, Set<PersonalGame> personalGameUser1, Set<PersonalGame> personalGameUser2, List<Note> notes) {
        this.creationDate = exchange.getCreationDate();
        this.lastUpdateDate = exchange.getLastUpdateDate();
        this.status = exchange.getStatus();
        this.eventDate = exchange.getEventDate();
        this.numberOfAttemps = exchange.getNumberOfAttemps();
        this.type = exchange.getType();
        this.wayExchange = exchange.getWayExchange();
        this.lastModifier = exchange.getLastModifier();
        this.user = exchange.getUser();
        this.personalGameUser1 = personalGameUser1;
        this.personalGameUser2 = personalGameUser2;
        this.notes = notes;
    }

    @NotNull
    @Past
    @JsonView(View.DetailsOfPersonalGame.class)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @NotNull
    @Past
    @JsonView(View.DetailsOfPersonalGame.class)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @JsonView(View.DetailsOfPersonalGame.class)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Past
    @JsonView(View.DetailsOfPersonalGame.class)
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Min(1)
    @JsonView(View.DetailsOfPersonalGame.class)
    public Integer getNumberOfAttemps() {
        return numberOfAttemps;
    }

    public void setNumberOfAttemps(Integer numberOfAttemps) {
        this.numberOfAttemps = numberOfAttemps;
    }

    @Enumerated(EnumType.STRING)
    @JsonView(View.DetailsOfPersonalGame.class)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @JsonView(View.DetailsOfPersonalGame.class)
    public String getWayExchange() {
        return wayExchange;
    }

    public void setWayExchange(String wayExchange) {
        this.wayExchange = wayExchange;
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

    @NotNull
    @JsonView(View.DetailsOfPersonalGame.class)
    public User getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(User lastModifier) {
        this.lastModifier = lastModifier;
    }

    @JsonView(View.DetailsOfPersonalGame.class)
    public Set<PersonalGame> getPersonalGameUser1() {
        return personalGameUser1;
    }

    public void setPersonalGameUser1(Set<PersonalGame> personalGameUser1) {
        this.personalGameUser1 = personalGameUser1;
    }

    @JsonView(View.DetailsOfPersonalGame.class)
    public Set<PersonalGame> getPersonalGameUser2() {
        return personalGameUser2;
    }

    public void setPersonalGameUser2(Set<PersonalGame> personalGameUser2) {
        this.personalGameUser2 = personalGameUser2;
    }

    @JsonView(View.DetailsOfPersonalGame.class)
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
