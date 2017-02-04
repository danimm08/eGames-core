package model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class PersonalGame extends BaseEntity {

    private String description;
    private Type type;
    private Integer numberOfViews;

    private Exchange exchangeUser1;
    private Exchange exchangeUser2;

    public PersonalGame() {
        super();
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(Integer numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    @ManyToOne
    public Exchange getExchangeUser1() {
        return exchangeUser1;
    }

    public void setExchangeUser1(Exchange exchangeUser1) {
        this.exchangeUser1 = exchangeUser1;
    }

    @ManyToOne
    public Exchange getExchangeUser2() {
        return exchangeUser2;
    }

    public void setExchangeUser2(Exchange exchangeUser2) {
        this.exchangeUser2 = exchangeUser2;
    }
}
