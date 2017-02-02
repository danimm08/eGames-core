package model;

import javax.persistence.*;

/**
 * Created by daniel on 2/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public abstract class BaseEntity {

    public BaseEntity(){
        super();
    }

    private long id;
    private long version;

    @Id
    @GeneratedValue
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Version
    public long getVersion(){
        return version;
    }

    public void setVersion(long version){
        this.version = version;
    }


    @Override
    public int hashCode() {
        return (int) this.getId();
    }

    @Override
    public boolean equals(Object other) {
        boolean result;

        if (this == other)
            result = true;
        else if (other == null)
            result = false;
        else if (other instanceof Integer)
            result = (this.getId() == (Integer) other);
        else if (!this.getClass().isInstance(other))
            result = false;
        else
            result = (this.getId() == ((BaseEntity) other).getId());

        return result;
    }

}
