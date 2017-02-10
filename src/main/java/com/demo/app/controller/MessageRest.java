package com.demo.app.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Message;
import com.demo.app.service.MessageService;

@Path("/")
@Component
public class MessageRest {

	@Autowired
	private MessageService messageService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/messages")
	public List<Message> message() {
		return messageService.getMessages();
	}
}


