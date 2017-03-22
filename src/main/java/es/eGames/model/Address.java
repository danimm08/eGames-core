package es.eGames.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;

/**
 * Created by daniel on 4/02/17.
 */
@Embeddable
public class Address {

    String street;
    String city;
    String state;
    String country;
    String zip;

    public Address() {
    }

    @NotBlank
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @NotBlank
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotBlank
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @NotBlank
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotBlank
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
