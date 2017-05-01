package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

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
public class Qualification extends BaseEntity {

    private String text;
    private Double mark;

    private Exchange exchange;
    private User user;

    public Qualification() {
        super();
    }

    @NotBlank
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Range(min=0, max=5)
    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonIgnore
    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @NotNull
    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
