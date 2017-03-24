package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.views.View;

import javax.persistence.*;

/**
 * Created by daniel on 2/02/17.
 */

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class BaseEntity {

    public BaseEntity() {

        super();
    }

    private int id;
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Base.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Version
    @Column(columnDefinition = "integer DEFAULT 0")
    @JsonView(View.Base.class)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
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
