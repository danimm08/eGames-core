package model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
public class Authority extends BaseEntity {

    private String name;

    @NotNull
    @Size(min = 0, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
