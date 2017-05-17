package com.demo.app.dao;

import java.io.Serializable;

import com.demo.app.domain.Entity;
import com.demo.app.repository.JpaGenericRepository;

public abstract class JpaDao<T extends Entity, K extends Serializable> implements CacheDao<T, K> {

	public abstract JpaGenericRepository<T, K> getJpaRepository();

	private Class<?> c;

	@Override
	public void setType(Class<?> c) {
		this.c = c;
	}

	@Override
	public Class<?> getType() {
		return this.c;
	}

	@Override
	public Iterable<T> findAll() {
		return getJpaRepository().findAll();
	}

	@Override
	public T findOne(K key) {
		return getJpaRepository().findOne(key);
	}

	@Override
	public void deleteOne(K key) {
		getJpaRepository().delete(key);
	}

	@Override
	public void updateOne(T item) {
		getJpaRepository().save(item);
	}

	@Override
	public void saveOne(T item) {
		getJpaRepository().save(item);
	}

}
