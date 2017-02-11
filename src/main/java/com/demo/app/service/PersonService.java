package com.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.PersonDao;

@Service
public class PersonService {

	@Autowired
	PersonDao personDao;

	public void generateFake(int numItems) {
		personDao.generateFake(numItems);
	}

}
