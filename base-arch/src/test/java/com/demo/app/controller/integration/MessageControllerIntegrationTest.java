package com.demo.app.controller.integration;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.app.domain.Message;

public class MessageControllerIntegrationTest extends ControllerIntegrationTest {

	@Test
	public void putGetDeleteAndGetMessage() {
		Message m = new Message("5000", "Author1", "Content");

		ResponseEntity<Message> responseEntity = launchRequest(m, "/app/rest/saveMessage", Message.class, RequestType.POST);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		responseEntity = launchRequest(null, "/app/rest/message/5000", Message.class, RequestType.GET);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		responseEntity = launchRequest(null, "/app/rest/deleteMessage/5000", null, RequestType.DELETE);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

		responseEntity = launchRequest(null, "/app/rest/message/5000", Message.class, RequestType.GET);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void put3Get3Delete3Message() {
		Message m1 = new Message("5000", "Author1", "Content1");
		launchRequest(m1, "/app/rest/saveMessage", Message.class, RequestType.POST);

		Message m2 = new Message("5001", "Author2", "Content2");
		launchRequest(m2, "/app/rest/saveMessage", Message.class, RequestType.POST);

		Message m3 = new Message("5002", "Author3", "Content3");
		launchRequest(m3, "/app/rest/saveMessage", Message.class, RequestType.POST);

		ResponseEntity<Message[]> responseEntity = launchRequest(null, "/app/rest/messages", Message[].class, RequestType.GET);

		Message[] messages = responseEntity.getBody();
		Assert.assertTrue(Arrays.asList(messages).contains(m1));
		Assert.assertTrue(Arrays.asList(messages).contains(m2));
		Assert.assertTrue(Arrays.asList(messages).contains(m3));

		launchRequest(null, "/app/rest/deleteMessage/5000", null, RequestType.DELETE);
		launchRequest(null, "/app/rest/deleteMessage/5001", null, RequestType.DELETE);
		responseEntity = launchRequest(null, "/app/rest/deleteMessage/5002", null, RequestType.DELETE);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void whenRoleAdminThenOkNoContent() {
		ResponseEntity<String> responseEntity = launchRequest(null, "/app/rest/onlyAdminMessage", String.class, RequestType.GET);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void whenNoRoleAdminThenForbidden() {
		this.setUser("user1");
		ResponseEntity<String> responseEntity = launchRequest(null, "/app/rest/onlyAdminMessage", String.class, RequestType.GET);
		Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
		this.setUser("krishna");
	}
}