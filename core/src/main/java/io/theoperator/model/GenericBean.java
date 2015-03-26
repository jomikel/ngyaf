package io.theoperator.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andreas on 2/19/15.
 */

@MappedSuperclass
@SequenceGenerator(name = "defaultSequence", sequenceName = "defaultSequence", allocationSize = 1, initialValue = 1)
public abstract class GenericBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "defaultSequence")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
