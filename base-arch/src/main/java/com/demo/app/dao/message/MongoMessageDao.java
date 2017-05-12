package com.demo.app.dao.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.dao.MongoDao;
import com.demo.app.domain.Message;
import com.demo.app.repository.MongoGenericRepository;
import com.demo.app.repository.message.MongoMessageRepository;

@Repository
public class MongoMessageDao extends MongoDao<Message, String> {

	@Autowired
	MongoMessageRepository mongoMessageRepository;

	@Override
	public MongoGenericRepository<Message, String> getMongoRepository() {
		return mongoMessageRepository;
	}
}
