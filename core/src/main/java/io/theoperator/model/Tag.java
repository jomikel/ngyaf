package io.theoperator.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by andreas on 3/4/15.
 */

@Entity
@SequenceGenerator(name = "defaultSequence", sequenceName = "tagSequence", allocationSize = 1, initialValue = 1)
@XmlRootElement(name="tags")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Tag extends GenericBean {

    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person")
    private Person person;

    public String getTag() {
        return tag;
    }

    @XmlElement
    public void setTag(String tag) {
        this.tag = tag;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
