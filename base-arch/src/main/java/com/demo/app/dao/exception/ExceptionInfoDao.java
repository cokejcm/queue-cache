package com.demo.app.dao.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.dao.MongoDao;
import com.demo.app.domain.exception.ExceptionInfo;
import com.demo.app.repository.MongoGenericRepository;
import com.demo.app.repository.exception.ExceptionInfoRepository;

@Repository
public class ExceptionInfoDao extends MongoDao<ExceptionInfo, String> {

	@Autowired
	private ExceptionInfoRepository exceptionInfoRepository;

	@Override
	public MongoGenericRepository<ExceptionInfo, String> getMongoRepository() {
		return this.exceptionInfoRepository;
	}

}
