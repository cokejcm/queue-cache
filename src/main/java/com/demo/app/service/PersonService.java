package com.demo.app.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.Person;

@Service
public class PersonService {

	@Autowired
	HzCacheDao<Person, Integer> personDao;

	@PostConstruct
	public void init() {
		personDao.setType(Person.class);
	}

	public void savePerson(Person person) {
		personDao.saveOne(person);
	}

}
