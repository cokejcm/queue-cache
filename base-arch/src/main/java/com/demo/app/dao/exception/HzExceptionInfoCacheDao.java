package com.demo.app.dao.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.app.dao.CacheDao;
import com.demo.app.dao.HzCacheDao;
import com.demo.app.domain.exception.ExceptionInfo;

@Repository
public class HzExceptionInfoCacheDao extends HzCacheDao<ExceptionInfo, String> {

	@Autowired
	private ExceptionInfoDao exceptionInfoDao;

	@Override
	public CacheDao<ExceptionInfo, String> getDao() {
		return this.exceptionInfoDao;
	}

}
