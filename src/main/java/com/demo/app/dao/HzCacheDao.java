package com.demo.app.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.demo.app.domain.Entity;
import com.hazelcast.core.HazelcastInstance;

public class HzCacheDao<T extends Entity<K>, K> implements CacheDao<T, K> {

	private Class<?> c;

	@Autowired
	private HazelcastInstance instance;
	@Autowired
	private Environment environment;
	@Autowired
	private Dao<T, K> dao;

	public Dao<T, K> getDao() {
		return dao;
	}

	public void setDao(Dao<T, K> dao) {
		this.dao = dao;
	}

	public boolean cacheActive() {
		return Arrays.asList(this.environment.getActiveProfiles()).contains("cache");
	}

	@Override
	public void setType(Class<?> c) {
		this.c = c;
	}

	@Override
	public Class<?> getType() {
		return this.c;
	}

	@Override
	public List<T> findAll() {
		if (cacheActive()) {
			// return
			// (List<T>)instance.getMap(getType().getSimpleName()).getAll();
		}
		return getDao().findAll();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findOne(K key) {
		if (cacheActive()) {
			return (T) instance.getMap(getType().getSimpleName()).get(key);
		}
		return getDao().findOne(key);
	}

	@Override
	public void deleteOne(K key) {
		getDao().deleteOne(key);
		if (cacheActive()) {
			instance.getMap(getType().getSimpleName()).remove(key);
		}
	}

	@Override
	public void updateOne(T item) {
		getDao().updateOne(item);
		if (cacheActive()) {
			instance.getMap(getType().getSimpleName()).put(item.getId(), item);
		}
	}

	@Override
	public void saveOne(T item) {
		getDao().saveOne(item);
		if (cacheActive()) {
			instance.getMap(getType().getSimpleName()).put(item.getId(), item);
		}
	}

}
