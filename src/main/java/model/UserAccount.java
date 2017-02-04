package model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by daniel on 4/02/17.
 */


@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends BaseEntity implements UserDetails {

    private String username;
    private String password;
    private Collection<Authority> authorities;

    public UserAccount() {
        super();

        this.authorities = new ArrayList<Authority>();
    }

    @Size(min = 5, max = 32)
    @Column(unique = true)
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min = 5, max = 32)
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @Valid
    @ElementCollection
    @Override
    public Collection<Authority> getAuthorities() {
        // WARNING: Should return an unmodifiable copy, but it's not possible with hibernate!
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        Assert.notNull(authority);
        Assert.isTrue(!authorities.contains(authority));

        authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        Assert.notNull(authority);
        Assert.isTrue(authorities.contains(authority));

        authorities.remove(authority);
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

}
