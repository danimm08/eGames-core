package es.eGames.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
public class Authority extends BaseEntity implements GrantedAuthority{

    private String authority;

    @NotNull
    @Size(min = 0, max = 50)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }



}
