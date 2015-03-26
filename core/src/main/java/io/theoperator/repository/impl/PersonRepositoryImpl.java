package io.theoperator.repository.impl;

import io.theoperator.model.Person;
import io.theoperator.repository.PersonRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/12/15.
 */


@Repository
public class PersonRepositoryImpl extends GenericRepositoryImpl<Person> implements PersonRepository {

	public PersonRepositoryImpl() {
		super(Person.class);
	}

}
