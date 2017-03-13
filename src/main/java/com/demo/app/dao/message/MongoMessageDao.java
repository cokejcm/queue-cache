package com.demo.app.dao.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.dao.MongoDao;
import com.demo.app.domain.Message;
import com.demo.app.repository.message.MongoMessageRepository;

@Repository
public class MongoMessageDao extends MongoDao<Message, String> {

	@Autowired
	MongoMessageRepository mongoMessageRepository;

	@Override
	public List<Message> findAll() {
		return mongoMessageRepository.findAll();
	}

	@Override
	public Message findOne(String key) {
		return mongoMessageRepository.findOne(key);
	}

	@Override
	public void deleteOne(String key) {
		mongoMessageRepository.delete(key);
	}

	@Override
	public void updateOne(Message item) {
		mongoMessageRepository.save(item);
	}

	@Override
	public void saveOne(Message item) {
		mongoMessageRepository.save(item);
	}

}
