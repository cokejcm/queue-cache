package com.demo.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Entity;
import com.demo.app.repository.MongoGenericRepository;

@Primary
@Repository
public class MongoDao<T extends Entity, K extends Serializable> implements CacheDao<T, K> {

	@Autowired
	@Qualifier("mongoGenericRepository")
	MongoGenericRepository<T, K> mongoRepository;

	private Class<?> c;

	@Override
	public void setType(Class<?> c) {
		this.c = c;
	}

	@Override
	public Class<?> getType() {
		return this.c;
	}

	public MongoGenericRepository<T, K> getMongoRepository() {
		return this.mongoRepository;
	}

	@Override
	public List<T> findAll() {
		return getMongoRepository().findAll();
	}

	@Override
	public T findOne(K key) {
		return getMongoRepository().findOne(key);
	}

	@Override
	public void deleteOne(K key) {
		getMongoRepository().delete(key);
	}

	@Override
	public T updateOne(T item) {
		return getMongoRepository().save(item);
	}

	@Override
	public T saveOne(T item) {
		return getMongoRepository().save(item);
	}

	@Override
	public void deleteAll() {
		//
	}
}
