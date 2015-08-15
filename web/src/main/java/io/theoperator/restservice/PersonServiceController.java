package io.theoperator.restservice;

import io.theoperator.model.Person;
import io.theoperator.service.PersonService;
import io.theoperator.utils.jqgrid.JqGridFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/24/15.
 */

@RestController
@RequestMapping("/persons")
public class PersonServiceController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    // PersonList is needed for convert Lists to XML
    @XmlRootElement(name="persons")
    @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
    private static class PersonList {
        @XmlElement(name = "person")
        public List<Person> persons;

        public PersonList() {
            this.persons = new ArrayList<>();
        }

        public PersonList(List<Person> persons) {
            this.persons = persons;
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public PersonList getAll() {
        return new PersonList(this.personService.list());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        this.personService.delete(Long.valueOf(id));
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Map<String, Object> getPage(
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
            @RequestParam("page") String page,
            @RequestParam("rows") String rows
    ) {
        return JqGridFormatter.format(this.personService.getPageList(sidx, sord, Integer.valueOf(page), Integer.valueOf(rows)), Integer.valueOf(page), Integer.valueOf(rows));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") String id) {
        return this.personService.getBean(Long.valueOf(id));
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = {"application/json", "application/xml"},
            consumes = {"application/x-www-form-urlencoded; charset=UTF-8"}
    )
    public Person updatePerson(@ModelAttribute Person person) {
        return this.personService.save(person);
    }

}
