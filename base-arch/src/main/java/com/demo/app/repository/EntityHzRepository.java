package com.demo.app.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.demo.app.domain.Entity;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.SqlPredicate;

public class EntityHzRepository<T extends Entity, K extends Serializable> {

	@Autowired(required = false)
	private HazelcastInstance instance;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Iterable<T> getEntitiesFiltered(Class<? extends Entity> clazz, String condition) {
		Iterable<T> iterable = new ArrayList(instance.getMap(clazz.getSimpleName()).values(new SqlPredicate(condition)));
		return !iterable.iterator().hasNext() ? null : iterable;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected T getEntityFiltered(Class<? extends Entity> clazz, String condition) {
		Iterable<T> elements = new ArrayList(instance.getMap(clazz.getSimpleName()).values(new SqlPredicate(condition)));
		if (elements.iterator().hasNext()) {
			return (T) new ArrayList(instance.getMap(clazz.getSimpleName()).values(new SqlPredicate(condition))).iterator().next();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Iterable<T> getEntitiesFiltered(Class<? extends Entity> clazz, String condition, Pageable p) {
		PagingPredicate pagingPredicate = new PagingPredicate<>(new SqlPredicate(condition), p.getPageSize());
		pagingPredicate.setPage(p.getPageNumber());
		Iterable<T> iterable = new ArrayList(instance.getMap(clazz.getSimpleName()).values(pagingPredicate));
		return !iterable.iterator().hasNext() ? null : iterable;
	}

}
