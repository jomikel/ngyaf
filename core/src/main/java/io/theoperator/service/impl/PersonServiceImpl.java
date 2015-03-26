package io.theoperator.service.impl;

import io.theoperator.model.Person;
import io.theoperator.repository.PersonRepository;
import io.theoperator.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/12/15.
 */

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person> implements PersonService {

	private PersonRepository personRepository;

	public PersonServiceImpl() {
		super();
	}

	@Autowired
	public PersonServiceImpl(PersonRepository repository) {
		super(repository);
		this.personRepository = repository;
	}
}
