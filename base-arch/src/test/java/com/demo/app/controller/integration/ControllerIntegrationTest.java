package com.demo.app.controller.integration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.app.Application;
import com.demo.app.domain.security.User;
import com.demo.app.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public abstract class ControllerIntegrationTest {

	@Autowired
	protected TestRestTemplate restTemplate;
	protected static HttpHeaders headers;
	private String user = "krishna";
	private String password = "12345";

	public void setUser(String user) {
		this.user = user;
		ControllerIntegrationTest.headers = null; //Force login
		generateToken();
	}

	public void setPassword(String password) {
		this.password = password;
		ControllerIntegrationTest.headers = null; //Force login
		generateToken();
	}

	protected enum RequestType {
		GET, POST, DELETE
	};

	@Before
	public void generateToken() {
		if (ControllerIntegrationTest.headers == null) {
			User user = new User();
			user.setUsername(this.user);
			user.setPassword(this.password);
			ResponseEntity<User> responseEntity = restTemplate.postForEntity("/app/rest/user/login", user, User.class);
			String token = responseEntity.getHeaders().get(Constants.AUTH_HEADER_NAME).get(0);
			// Set the token in the header
			ControllerIntegrationTest.headers = new HttpHeaders();
			ControllerIntegrationTest.headers.setContentType(MediaType.APPLICATION_JSON);
			ControllerIntegrationTest.headers.set(Constants.AUTH_HEADER_NAME, token);
		}
	}

	protected <T> ResponseEntity<T> launchRequest(T entity, String url, Class<T> c, RequestType reqType) {
		HttpEntity<T> httpEntity = new HttpEntity<T>(entity, ControllerIntegrationTest.headers);
		if (reqType == RequestType.POST) {
			return restTemplate.postForEntity(url, httpEntity, c);
		} else if (reqType == RequestType.GET) {
			return restTemplate.exchange(url, HttpMethod.GET, httpEntity, c);
		} else {
			return restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, c);
		}
	}
}