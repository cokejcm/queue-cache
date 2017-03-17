package com.demo.app.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.app.Application;
import com.demo.app.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class PersonControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void putPerson() {
		Person p = new Person("1", "Name", "Adress", "email", "company");
		ResponseEntity<Person> responseEntity = restTemplate.postForEntity("/app/rest/savePerson", p, Person.class);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		restTemplate.delete("/app/rest/deletePerson/1");
	}

}