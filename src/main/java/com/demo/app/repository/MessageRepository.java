package com.demo.app.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Message;

@Repository
//@Profile("hazelcast")
public interface MessageRepository extends MongoRepository<Message, String> {

	@Cacheable(cacheNames = "usercache",key = "{#author}")
	public List<Message> findByAuthor(String author);

}
