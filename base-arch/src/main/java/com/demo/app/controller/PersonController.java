package com.demo.app.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Person;
import com.demo.app.service.PersonService;

@Profile("dev")
@Path("/")
@Component
public class PersonController {

	@Autowired
	private PersonService personService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/savePerson")
	public void savePerson(Person person) {
		personService.savePerson(person);
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deletePerson/{id}")
	public void deletePerson(@PathParam("id") String id) {
		personService.deletePerson(id);
	}

}
