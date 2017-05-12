package com.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.HzCacheDao;
import com.demo.app.dao.message.HzMessageCacheDao;
import com.demo.app.domain.Message;

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

	public List<Message> getMessages() {
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
