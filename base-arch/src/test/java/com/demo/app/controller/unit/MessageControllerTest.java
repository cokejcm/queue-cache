package com.demo.app.controller.unit;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.app.configuration.jersey.JerseyConfig;
import com.demo.app.controller.MessageController;
import com.demo.app.domain.Message;
import com.demo.app.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageControllerTest extends JerseyTest {

	@InjectMocks
	private MessageController restResource;
	@Mock
	private MessageService messageService;

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		MockitoAnnotations.initMocks(this);
		return ServletDeploymentContext.forServlet(new ServletContainer(new JerseyConfig().register(restResource))).build();
	}

	@Test
	public void testMessages() {
		List<Message> messages = new ArrayList<Message>();
		Message message = new Message();
		message.setAuthor("Author");
		message.setContents("Content");
		messages.add(message);
		Mockito.when(messageService.getMessages()).thenReturn(messages);
		final String messageOutput = target("messages").request().get(String.class);
		Assert.assertTrue(messageOutput.contains(message.getAuthor()) && messageOutput.contains(message.getContents()));
	}
}
