package com.demo.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

	public List<Message> findByAuthor(String author);

}
