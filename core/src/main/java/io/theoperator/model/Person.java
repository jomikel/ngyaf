package io.theoperator.model;

/**
 * Created by andreas on 2/12/15.
 */

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@Entity
@SequenceGenerator(name = "defaultSequence", sequenceName = "personSequence", allocationSize = 1, initialValue = 1)
@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Person extends GenericBean {

    public Person() {

    }

    private String firstName;
    private String lastName;
    private String eMail;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Tag> tagList;

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    @XmlElement
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @XmlElement
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public List<Tag> getTagList() {
        return tagList;
    }

    @XmlElement
    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

}

