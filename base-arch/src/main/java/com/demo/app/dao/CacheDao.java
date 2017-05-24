package com.demo.app.dao;

import com.demo.app.domain.Entity;

/**
 *
 * @author coke Dao Generic Interface
 * @param <T>
 * @param <K>
 */
public interface CacheDao<T extends Entity, K> {

	public Iterable<T> findAll();

	public T findOne(K key);

	public void deleteOne(K key);

	public T updateOne(T item);

	public T saveOne(T item);

	public void setType(Class<?> c);

	public Class<?> getType();

}
