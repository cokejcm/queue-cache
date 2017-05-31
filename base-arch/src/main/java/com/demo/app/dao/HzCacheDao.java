package com.demo.app.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.demo.app.configuration.cache.Cacheable;
import com.demo.app.domain.Entity;
import com.demo.app.util.Util;
import com.hazelcast.core.HazelcastInstance;

@Repository
public class HzCacheDao<T extends Entity, K extends Serializable> implements CacheDao<T, K> {

	private Class<?> c;

	@Autowired(required = false)
	private HazelcastInstance instance;
	@Autowired
	private Environment environment;
	@Autowired
	private CacheDao<T, K> dao;

	public CacheDao<T, K> getDao() {
		return this.dao;
	}

	public boolean cacheActive() {
		return Arrays.asList(this.environment.getActiveProfiles()).contains("cache");
	}

	public boolean isCacheable() {
		return cacheActive() && c.isAnnotationPresent(Cacheable.class);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable<T> findAll() {
		if (isCacheable()) {
			List<T> elements = new ArrayList(instance.getMap(getType().getSimpleName()).values());
			if (!elements.isEmpty()) {
				return elements;
			}
		}
		return getDao().findAll();
	}

	public void cacheAll() {
		if (isCacheable()) {
			instance.getMap(getType().getSimpleName()).putAll(Util.ListToMap(getDao().findAll()));
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findOne(K key) {
		T item;
		if (isCacheable()) {
			item = (T) instance.getMap(getType().getSimpleName()).get(key);
			if (item == null) {
				item = getDao().findOne(key);
				if (item != null) {
					instance.getMap(getType().getSimpleName()).set(item.getId(), item);
				}
			}
		} else {
			item = getDao().findOne(key);
		}
		return item;
	}

	@Override
	public void deleteOne(K key) {
		getDao().deleteOne(key);
		if (isCacheable()) {
			instance.getMap(getType().getSimpleName()).remove(key);
		}
	}

	@Override
	public T updateOne(T item) {
		return getDao().updateOne(item);
	}

	@Override
	public T saveOne(T item) {
		return getDao().saveOne(item);
	}

}
