package es.eGames.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by daniel on 4/02/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Exchange extends BaseEntity{

    private Date creationDate;
    private Date lastUpdateDate;
    private Boolean status;
    private Date eventDate;
    private Integer numberOfAttemps;
    private Type type;
    private String wayExchange;

    private User user;


    public Exchange() {
        super();
    }

    @NotNull
    @Past
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @NotNull
    @Past
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Past
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Min(1)
    public Integer getNumberOfAttemps() {
        return numberOfAttemps;
    }

    public void setNumberOfAttemps(Integer numberOfAttemps) {
        this.numberOfAttemps = numberOfAttemps;
    }

    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getWayExchange() {
        return wayExchange;
    }

    public void setWayExchange(String wayExchange) {
        this.wayExchange = wayExchange;
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
