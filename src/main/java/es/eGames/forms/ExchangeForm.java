package es.eGames.forms;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.model.Note;
import es.eGames.model.PersonalGame;
import es.eGames.model.Type;
import es.eGames.views.View;

import java.util.List;

/**
 * Created by daniel on 9/03/17.
 */
public class ExchangeForm {

    private List<PersonalGame> personalGamesUser1;
    private List<PersonalGame> personalGamesUser2;
    private Type type;
    private String wayExchange;
    private List<Note> notes;

    public ExchangeForm() {
        super();
    }

    //TODO: Add annotations here to validate the form

    @JsonView(View.DetailsOfPersonalGame.class)
    public List<PersonalGame> getPersonalGamesUser1() {
        return personalGamesUser1;
    }

    public void setPersonalGamesUser1(List<PersonalGame> personalGamesUser1) {
        this.personalGamesUser1 = personalGamesUser1;
    }

    @JsonView(View.DetailsOfPersonalGame.class)
    public List<PersonalGame> getPersonalGamesUser2() {
        return personalGamesUser2;
    }

    public void setPersonalGamesUser2(List<PersonalGame> personalGamesUser2) {
        this.personalGamesUser2 = personalGamesUser2;
    }

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

    @JsonView(View.DetailsOfPersonalGame.class)
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}