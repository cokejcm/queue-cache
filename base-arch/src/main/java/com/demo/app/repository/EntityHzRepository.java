package com.demo.app.repository;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.app.domain.Entity;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.query.SqlPredicate;

public class EntityHzRepository<T extends Entity, K extends Serializable>  {

	@Autowired(required = false)
	private HazelcastInstance instance;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Iterable<T> getEntitiesFiltered(Class<? extends Entity> clazz, String condition){
		return new ArrayList(instance.getMap(clazz.getSimpleName()).values(new SqlPredicate(condition)));
	}
}
