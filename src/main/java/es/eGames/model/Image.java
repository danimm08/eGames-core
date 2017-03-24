package es.eGames.model;


import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;
import es.eGames.views.View;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by daniel on 4/02/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Image extends BaseEntity {

    private String pathUrl;

    private PersonalGame personalGame;

    public Image() {
        super();
    }

    @NotBlank
    @JsonView(View.DetailsOfPersonalGame.class)
    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    @NotNull
    @ManyToOne(optional = false)
    public PersonalGame getPersonalGame() {
        return personalGame;
    }

    public void setPersonalGame(PersonalGame personalGame) {
        this.personalGame = personalGame;
    }
}