package com.demo.app.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Message;
import com.demo.app.service.MessageService;

@Path("/")
@Component
public class MessageRest {

	// static Logger log = Logger.getLogger(MessageRest.class.getName());

	@Autowired
	private MessageService messageService;

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Path("/messages") public List<Message> messages() { return
	 * messageService.getMessages();
	 * 
	 * }
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/message/{id}")
	public Message messageById(@PathParam("id") String id) {
		// log.info("Get by Author");
		return messageService.getMessage(id);
	}

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Path("/messageAuthor/{author}") public List<Message>
	 * messageByAuthor(@PathParam("author") String author) { //
	 * log.info("Get by Author"); return
	 * messageService.getMessagesByAuthor(author); }
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveMessage")
	public void saveMessage(Message message) {
		messageService.saveMessage(message);
	}
}
