package com.demo.app.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.demo.app.domain.Entity;
import com.demo.app.repository.JpaGenericRepository;

@Repository
public class JpaDao<T extends Entity, K extends Serializable> implements CacheDao<T, K> {

	JpaGenericRepository<T, K> jpaGenericRepository;

	public JpaGenericRepository<T, K> getJpaRepository() {
		return this.jpaGenericRepository;
	}

	public void setJpaGenericRepository(JpaGenericRepository<T, K> jpaGenericRepository) {
		this.jpaGenericRepository = jpaGenericRepository;
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
	public T updateOne(T item) {
		return getJpaRepository().save(item);
	}

	@Override
	public T saveOne(T item) {
		return getJpaRepository().save(item);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteAll() {
		Iterable<T> items = findAll();
		for (T t : items) {
			deleteOne((K)t.getId());
		}
	}
}
