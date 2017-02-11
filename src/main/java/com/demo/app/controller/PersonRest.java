package com.demo.app.controller;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.app.service.PersonService;

@Path("/person")
@Component
public class PersonRest {

	@Autowired
	private PersonService personService;

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/generateFake/{numItems}")
	public String generateFake(@PathParam("numItems") int numItems) {
		personService.generateFake(numItems);
		return numItems + " Items of type Person inserted";
	}
}
