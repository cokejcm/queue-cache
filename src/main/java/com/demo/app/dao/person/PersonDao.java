package com.demo.app.dao.person;

import org.springframework.stereotype.Repository;

import com.demo.app.domain.Person;

import io.bloco.faker.Faker;

@Repository
public class PersonDao {

	private void generateData(int numRows) {
		for (int i = 0; i < numRows; i++) {
			Faker faker = new Faker();
			Person p = new Person();
			p.setId(i);
			p.setAddress(faker.address.streetAddress());
			p.setEmail(faker.internet.email());
			p.setName(faker.name.firstName());
			p.setCompany(faker.company.name());
			// savePerson(p);
		}
	}

	public void generateFake(int numItems) {
		generateData(numItems);
	}

}
