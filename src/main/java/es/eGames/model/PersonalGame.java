package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.views.View;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class PersonalGame extends BaseEntity {

    private String description;
    private Type type;
    private Integer numberOfViews;
    private Double distance;


    private Exchange exchange;

    private Game game;
    private User user;
    private Set<Image> images;

    public PersonalGame() {
        super();
    }

    @NotBlank
    @JsonView(View.ListPersonalGame.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(View.ListPersonalGame.class)
    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @JsonView(View.ListPersonalGame.class)
    public Integer getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(Integer numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    @Transient
//    @JsonProperty
    @JsonProperty
    @JsonView(View.ListPersonalGame.class)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @ManyToOne
    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @ManyToOne(optional = false)
    @NotNull
    @JsonView(View.ListPersonalGame.class)
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne(optional = false)
    @NotNull
    @JsonView(View.ListPersonalGame.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Auxiliar relationship

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "personalGame")
    @JsonView(View.ListPersonalGame.class)
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
