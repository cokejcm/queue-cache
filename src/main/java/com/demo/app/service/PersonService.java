package com.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.Person;

@Service
public class PersonService extends com.demo.app.service.Service<Person, String> {

	@Autowired
	private HzCacheDao<Person, String> cacheDao;

	@Override
	public Class<?> getType() {
		return Person.class;
	}

	@Override
	public HzCacheDao<Person, String> getCacheDao() {
		return this.cacheDao;

	}

	public void savePerson(Person person) {
		getCacheDao().saveOne(person);
	}

	public void deletePerson(String id) {
		getCacheDao().deleteOne(id);
	}

}
