package model;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by daniel on 4/02/17.
 */

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority {

    public Authority() {
        super();
    }

    public static final String USER = "USER";

    private String authority;

    @NotBlank
    @Pattern(regexp = "^" + USER + "$")
    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public static Collection<Authority> listAuthorities() {
        Collection<Authority> result;
        Authority authority;

        result = new ArrayList<Authority>();

        authority = new Authority();
        authority.setAuthority(USER);
        result.add(authority);


        return result;
    }

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (!this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getAuthority().equals(((Authority) other)
                    .getAuthority()));

        return result;
    }

}
