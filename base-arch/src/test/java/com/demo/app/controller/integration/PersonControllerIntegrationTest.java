package com.demo.app.controller.integration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.app.domain.Person;

public class PersonControllerIntegrationTest extends ControllerIntegrationTest {

	@Test
	public void putPerson() {
		Person p = new Person("1", "Name", "Adress", "email", "company");
		ResponseEntity<Person> responseEntity = launchRequest(p, "/app/rest/savePerson", Person.class, RequestType.POST);

		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		responseEntity = launchRequest(null, "/app/rest/deletePerson/1", null, RequestType.DELETE);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
}