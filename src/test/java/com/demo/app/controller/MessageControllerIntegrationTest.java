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
import com.demo.app.domain.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class MessageControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void putAndGetMessage() {
		ResponseEntity<Message> responseEntity = restTemplate.getForEntity("/app/rest/message/42", Message.class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}