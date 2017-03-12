package com.demo.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Entity;
import com.demo.app.repository.MongoGenericRepository;

@Repository
public class MongoDao<T extends Entity<K>, K extends Serializable> implements Dao<T, K> {

	@Autowired
	MongoGenericRepository<T, K> mongoGenericRepository;

	@Override
	public List<T> findAll() {
		return mongoGenericRepository.findAll();
	}

	@Override
	public T findOne(K key) {
		return mongoGenericRepository.findOne(key);
	}

	@Override
	public void deleteOne(K key) {
		mongoGenericRepository.delete(key);

	}

	@Override
	public void updateOne(T item) {
		mongoGenericRepository.save(item);
	}

	@Override
	public void saveOne(T item) {
		mongoGenericRepository.save(item);
	}

}
