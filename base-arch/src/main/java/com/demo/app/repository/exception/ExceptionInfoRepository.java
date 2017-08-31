package com.demo.app.repository.exception;

import org.springframework.stereotype.Repository;

import com.demo.app.domain.exception.ExceptionInfo;
import com.demo.app.repository.MongoGenericRepository;

@Repository
public interface ExceptionInfoRepository extends MongoGenericRepository<ExceptionInfo, String> {

}
