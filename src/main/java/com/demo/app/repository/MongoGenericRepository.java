package com.demo.app.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.domain.Entity;

@Repository
public interface MongoGenericRepository<T extends Entity, K extends Serializable> extends MongoRepository<T, K> {

}
