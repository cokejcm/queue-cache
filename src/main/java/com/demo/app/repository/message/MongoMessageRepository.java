package com.demo.app.repository.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Message;

@Repository
public interface MongoMessageRepository extends MongoRepository<Message, String> {

}
