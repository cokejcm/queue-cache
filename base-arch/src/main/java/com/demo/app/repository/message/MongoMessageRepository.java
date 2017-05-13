package com.demo.app.repository.message;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Message;
import com.demo.app.repository.MongoGenericRepository;

@Profile("dev")
@Repository
public interface MongoMessageRepository extends MongoGenericRepository<Message, String> {

}
