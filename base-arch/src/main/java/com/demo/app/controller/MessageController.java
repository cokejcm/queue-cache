package com.demo.app.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.demo.app.configuration.internationalization.MessageSourceLocale;
import com.demo.app.domain.Message;
import com.demo.app.service.MessageService;

@Profile("dev")
@Path("/")
@Component
public class MessageController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageService messageService;
	@Autowired
	private MessageSourceLocale messageSource;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/messages")
	public Iterable<Message> messages() {
		return messageService.getMessages();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/message/{id}")
	public Message messageById(@PathParam("id") String id) {
		return messageService.getMessage(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveMessage")
	public void saveMessage(Message message) {
		messageService.saveMessage(message);
		messageService.findOne(message.getId());
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateMessage")
	public void updateMessage(Message message) {
		messageService.saveMessage(message);
		messageService.findOne(message.getId());
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deleteMessage/{id}")
	public void deleteMessage(@PathParam("id") String id) {
		messageService.deleteMessage(id);
	}

	@GET
	@Path("/onlyAdminMessage")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Produces(MediaType.APPLICATION_JSON)
	public String onlyAdmin() {
		return "ACCESSED";
	}

	@GET
	@Path("/messageLocal")
	@Produces(MediaType.TEXT_PLAIN)
	public String messageLocal() {
		logger.info("Hola");
		return messageSource.getMessage("welcome.message", new Object[] { "John Doe" });
	}
}
