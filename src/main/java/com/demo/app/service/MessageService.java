package com.demo.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.MessageDao;
import com.demo.app.domain.Message;

@Service
public class MessageService {

	List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

	@Autowired
	MessageDao messageDao;

	@PostConstruct
	public void init() {
		messages.add(new Message("Joe", "Hello"));
		messages.add(new Message("Jane", "Spring boot is cool !"));
	}

	public List<Message> getMessages() {
		return messages;
	}

	public List<Message> getMessagesByAuthor(String author) {
		return messageDao.getMessagesByAuthor(author);
	}

}
