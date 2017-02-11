package com.demo.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Message;
import com.demo.app.repository.MessageRepository;

@Repository
public class MessageDao {

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> getMessagesByAuthor(String author) {
		return messageRepository.findByAuthor(author);
	}

}
