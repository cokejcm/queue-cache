package com.demo.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.app.Application;
import com.demo.app.domain.Message;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class MessageControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createClient() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/*Message message = new Message();
		message.setId("50");
		message.setContents("Coke2");
		message.setAuthor("Coke2");*/
		//restTemplate.postForObject("/app/rest/message/50", message, Message.class);
		System.out.println("HOLA");

		Object object = restTemplate.getForObject("/app/rest/message/95", Object.class);
		mapper.convertValue(object, Message.class);
		System.out.println(object);
		ResponseEntity<Message> r = restTemplate.getForEntity("/app/rest/message/95", Message.class);
		System.out.println(r.getBody());
		// System.out.println(responseEntity.getStatusCodeValue());
		// Message message = responseEntity.getBody();
		// System.out.println("hola2:" + );
		// System.out.println(message.getContents());
		/*
		 * assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		 * assertEquals("Foo", client.getName());
		 */
	}
}