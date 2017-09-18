package com.demo.app.service.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.HzCacheDao;
import com.demo.app.dao.exception.HzExceptionInfoCacheDao;
import com.demo.app.domain.exception.ExceptionInfo;

@Service
public class ExceptionInfoService extends com.demo.app.service.Service<ExceptionInfo, String> {

	@Autowired
	private HzExceptionInfoCacheDao hzExceptionInfoCacheDao;

	@Override
	public Class<?> getType() {
		return ExceptionInfo.class;
	}

	@Override
	public HzCacheDao<ExceptionInfo, String> getCacheDao() {
		return this.hzExceptionInfoCacheDao;
	}

	public Iterable<ExceptionInfo> getExceptionInfos() {
		return getCacheDao().findAll();
	}

	public ExceptionInfo getExceptionInfo(String id) {
		return getCacheDao().findOne(id);
	}

	public void deleteExceptionInfo(String id) {
		getCacheDao().deleteOne(id);
	}

	public void saveExceptionInfo(ExceptionInfo exceptionInfo) {
		getCacheDao().saveOne(exceptionInfo);
	}

	public void updateExceptionInfo(ExceptionInfo exceptionInfo) {
		getCacheDao().updateOne(exceptionInfo);
	}

}
