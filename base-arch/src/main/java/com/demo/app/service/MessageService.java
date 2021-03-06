package com.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.demo.app.dao.HzCacheDao;
import com.demo.app.dao.message.HzMessageCacheDao;
import com.demo.app.domain.Message;

@Profile("dev")
@Service
public class MessageService extends com.demo.app.service.Service<Message, String> {

	@Autowired
	HzMessageCacheDao cacheDao;

	@Override
	public Class<?> getType() {
		return Message.class;
	}

	@Override
	public HzCacheDao<Message, String> getCacheDao() {
		return this.cacheDao;
	}

	public Iterable<Message> getMessages() {
		return getCacheDao().findAll();
	}

	public Message getMessage(String id) {
		return getCacheDao().findOne(id);
	}

	public void deleteMessage(String id) {
		getCacheDao().deleteOne(id);
	}

	public void saveMessage(Message message) {
		getCacheDao().saveOne(message);
	}

	public void updateMessage(Message message) {
		getCacheDao().updateOne(message);
	}

}
