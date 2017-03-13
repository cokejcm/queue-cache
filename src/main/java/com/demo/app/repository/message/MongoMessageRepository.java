package com.demo.app.repository.message;

import org.springframework.stereotype.Repository;

import com.demo.app.domain.Message;
import com.demo.app.repository.MongoGenericRepository;

@Repository
public interface MongoMessageRepository extends MongoGenericRepository<Message, String> {

}
