package com.demo.app.controller;

import java.util.Arrays;

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
	public void putGetDeleteAndGetMessage() {
		Message m = new Message("5000", "Author1", "Content");
		ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/app/rest/saveMessage", m, Message.class);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		responseEntity = restTemplate.getForEntity("/app/rest/message/5000", Message.class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		restTemplate.delete("/app/rest/deleteMessage/5000");

		responseEntity = restTemplate.getForEntity("/app/rest/message/5000", Message.class);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void put3Get3Delete3Message() {
		Message m1 = new Message("5000", "Author1", "Content1");
		restTemplate.postForEntity("/app/rest/saveMessage", m1, Message.class);
		Message m2 = new Message("5001", "Author2", "Content2");
		restTemplate.postForEntity("/app/rest/saveMessage", m2, Message.class);
		Message m3 = new Message("5002", "Author3", "Content3");
		restTemplate.postForEntity("/app/rest/saveMessage", m3, Message.class);

		ResponseEntity<Message[]> responseEntity = restTemplate.getForEntity("/app/rest/messages", Message[].class);

		Message[] messages = responseEntity.getBody();
		Assert.assertTrue(Arrays.asList(messages).contains(m1));
		Assert.assertTrue(Arrays.asList(messages).contains(m2));
		Assert.assertTrue(Arrays.asList(messages).contains(m3));

		restTemplate.delete("/app/rest/deleteMessage/5000");
		restTemplate.delete("/app/rest/deleteMessage/5001");
		restTemplate.delete("/app/rest/deleteMessage/5002");
	}
}