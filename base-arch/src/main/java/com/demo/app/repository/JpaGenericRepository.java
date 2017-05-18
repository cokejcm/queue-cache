package com.demo.app.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.demo.app.domain.Entity;

@NoRepositoryBean
public interface JpaGenericRepository<T extends Entity, K extends Serializable> extends CrudRepository<T, K> {

}
