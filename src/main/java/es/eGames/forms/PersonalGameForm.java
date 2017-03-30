package es.eGames.forms;

import es.eGames.model.Game;
import es.eGames.model.Type;

/**
 * Created by daniel on 30/03/17.
 */
public class PersonalGameForm {

    private String description;
    private Type type;
    private Game game;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
}
