package com.demo.app.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Entity;
import com.demo.app.repository.JpaGenericRepository;

@Repository
public class JpaDao<T extends Entity, K extends Serializable> implements CacheDao<T, K> {

	@Autowired(required = false)
	JpaGenericRepository<T, K> jpaGenericRepository;

	public JpaGenericRepository<T, K> getJpaRepository() {
		return this.jpaGenericRepository;
	}

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
