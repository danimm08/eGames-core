package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;
import es.eGames.views.View;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Platform extends BaseEntity {

    private String name;


    public Platform() {
        super();
    }

    @NotBlank
    @JsonView(View.ListPersonalGame.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
