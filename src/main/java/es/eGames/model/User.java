package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import es.eGames.serializers.CustomFollowersFolloweesSerializer;
import es.eGames.views.View;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User extends BaseEntity {

    private String name;
    private String surname;
    private Double reputation;
    private String profilePicture;
    private Address address;
    private Integer nExchanges;

    private Collection<User> followers;
    private Collection<User> followees;
    private UserAccount userAccount;

    public User() {
        super();
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Range(min = 0, max = 10)
    @JsonView(View.DetailsOfPersonalGame.class)
    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }


    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @NotNull
    @Valid
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @NotNull
    @Range(min=0)
    public Integer getnExchanges() {
        return nExchanges;
    }

    public void setnExchanges(Integer nExchanges) {
        this.nExchanges = nExchanges;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonSerialize(using = CustomFollowersFolloweesSerializer.class)
    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> followers) {
        this.followers = followers;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonSerialize(using = CustomFollowersFolloweesSerializer.class)
    public Collection<User> getFollowees() {
        return followees;
    }

    public void setFollowees(Collection<User> followees) {
        this.followees = followees;
    }

    @NotNull
    @Valid
    @OneToOne(optional = false)
    @JsonView(View.DetailsOfPersonalGame.class)
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
