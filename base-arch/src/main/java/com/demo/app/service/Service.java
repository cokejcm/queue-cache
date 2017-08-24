package com.demo.app.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import com.demo.app.configuration.AppException;
import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.Entity;

public abstract class Service<T extends Entity, K extends Serializable> {

	public abstract Class<?> getType();

	public abstract HzCacheDao<T, K> getCacheDao();

	@PostConstruct
	public void init() {
		getCacheDao().setType(getType());
	}

	public void cacheAll() {
		getCacheDao().cacheAll();
	}

	public T findOne(K key) {
		return getCacheDao().findOne(key);
	}

	public Iterable<T> findAll() {
		return getCacheDao().findAll();
	}

	public void deleteOne(K key) throws AppException {
		getCacheDao().deleteOne(key);
	}

	public T updateOne(T item) {
		return getCacheDao().updateOne(item);
	}

	public T saveOne(T item) throws AppException {
		return getCacheDao().saveOne(item);
	}
}
