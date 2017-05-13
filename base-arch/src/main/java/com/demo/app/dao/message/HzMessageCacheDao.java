package com.demo.app.dao.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.demo.app.dao.CacheDao;
import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.Message;

@Profile("dev")
@Repository
public class HzMessageCacheDao extends HzCacheDao<Message, String> {

	@Autowired
	MongoMessageDao mongoMessageDao;

	@Override
	public CacheDao<Message, String> getDao() {
		return mongoMessageDao;
	}

}
