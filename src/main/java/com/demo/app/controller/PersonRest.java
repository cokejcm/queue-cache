package com.demo.app.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Person;
import com.demo.app.service.PersonService;

@Path("/")
@Component
public class PersonRest {

	@Autowired
	private PersonService personService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/savePerson")
	public void savePerson(Person person) {
		personService.savePerson(person);
	}

}
