package com.demo.app.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.app.domain.Entity;

@NoRepositoryBean
public interface JpaGenericRepository<T extends Entity, K extends Serializable> extends PagingAndSortingRepository<T, K> {

}
