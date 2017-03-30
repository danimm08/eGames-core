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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        return zip != null ? zip.equals(address.zip) : address.zip == null;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        return result;
    }
}
