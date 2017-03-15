package com.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.Person;

@Service
public class PersonService extends com.demo.app.service.Service<Person, Integer> {

	@Autowired
	private HzCacheDao<Person, Integer> cacheDao;

	@Override
	public Class<?> getType() {
		return Person.class;
	}

	@Override
	public HzCacheDao<Person, Integer> getCacheDao() {
		return this.cacheDao;

	}

	public void savePerson(Person person) {
		getCacheDao().saveOne(person);
	}

}
