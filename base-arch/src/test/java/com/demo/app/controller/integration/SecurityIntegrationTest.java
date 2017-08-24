package com.demo.app.controller.integration;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.app.Application;
import com.demo.app.configuration.security.TokenHandler;
import com.demo.app.domain.Message;
import com.demo.app.domain.security.User;
import com.demo.app.service.security.UserService;
import com.demo.app.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecurityIntegrationTest {

	private static String token;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private UserService userService;

	@Test
	public void A_WhenLoginOkThenGenerateToken() {
		User user = new User();
		user.setUsername("krishna");
		user.setPassword("12345");
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("/app/rest/user/login", user, User.class);
		// Response code 204
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		String token = responseEntity.getHeaders().get(Constants.AUTH_HEADER_NAME).get(0);
		TokenHandler tokenHandler = new TokenHandler(Constants.TOKEN_KEY, userService);
		// User encrypted in the token is the same as the one originally sent
		Assert.assertEquals(user.getUsername(), tokenHandler.parseUserFromToken(token).getUsername());
		// The token will be reused by other tests
		SecurityIntegrationTest.token = token;
	}

	@Test
	public void B_WhenNoTokenThenForbidden() {
		Message m1 = new Message("5000", "Author1", "Content1");
		ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/app/rest/saveMessage", m1, Message.class);
		// Response code 403 Forbidden
		Assert.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
	}

	@Test
	public void C_WhenTokenThenInsertOkResultNoContent() {
		Message m1 = new Message("5000", "Author1", "Content1");
		// Set the token in the header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(Constants.AUTH_HEADER_NAME, SecurityIntegrationTest.token);
		HttpEntity<Message> entity = new HttpEntity<Message>(m1, headers);
		ResponseEntity<Message> responseEntity = restTemplate.postForEntity("/app/rest/saveMessage", entity, Message.class);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		restTemplate.delete("/app/rest/deleteMessage/5000");
	}

	// Send a Expired a token

	// Send invalid credentials
	@Test
	public void D_WhenInvalidCredentialsThenUnathorized() {
		User user = new User();
		user.setUsername("krishna");
		user.setPassword("123456");
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("/app/rest/user/login", user, User.class);
		// Invalid credentials
		Assert.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}

}