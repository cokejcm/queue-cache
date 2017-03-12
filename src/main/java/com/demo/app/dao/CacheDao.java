package com.demo.app.dao;

import java.util.List;

/**
 *
 * @author coke Dao Generic Interface
 * @param <T>
 * @param <K>
 */
public interface CacheDao<T, K> {

	public List<T> findAll();

	public T findOne(K key);

	public void deleteOne(K key);

	public void updateOne(T item);

	public void saveOne(T item);

	public void setType(Class<?> c);

	public Class<?> getType();

}
