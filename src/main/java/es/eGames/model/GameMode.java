package es.eGames.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class GameMode extends BaseEntity {

    private String name;

    public GameMode() {
        super();
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
