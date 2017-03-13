package com.demo.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Entity;
import com.demo.app.repository.MongoGenericRepository;

@Repository
public class MongoDao<T extends Entity<K>, K extends Serializable> implements Dao<T, K> {

	@Autowired
	@Qualifier("mongoGenericRepository")
	MongoGenericRepository<T, K> mongoRepository;

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
	public void updateOne(T item) {
		getMongoRepository().save(item);
	}

	@Override
	public void saveOne(T item) {
		getMongoRepository().save(item);
	}

}
