package com.demo.app.dao.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.dao.Dao;
import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.Message;

@Repository
public class HzMessageCacheDao extends HzCacheDao<Message, String> {

	@Autowired
	MongoMessageDao mongoMessageDao;

	@Override
	public Dao<Message, String> getDao() {
		return mongoMessageDao;
	}



}
