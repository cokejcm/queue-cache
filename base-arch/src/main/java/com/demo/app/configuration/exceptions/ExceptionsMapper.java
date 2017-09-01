package com.demo.app.configuration.exceptions;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.demo.app.configuration.internationalization.MessageSourceLocale;
import com.demo.app.domain.exception.ExceptionInfo;
import com.demo.app.domain.exception.ExceptionType;
import com.demo.app.service.exception.ExceptionInfoService;

@Provider
public class ExceptionsMapper implements ExceptionMapper<Exception> {

	@Autowired
	ExceptionInfoService exceptionInfoService;
	@Autowired
	private MessageSourceLocale messageSource;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Response toResponse(Exception e) {
		logger.error(e.getMessage(), e);
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ExceptionType type = AppExceptionInterface.class.isAssignableFrom(e.getClass()) ? ExceptionType.APP_EXCEPTION : ExceptionType.EXCEPTION;
		ExceptionInfo excInfo = new ExceptionInfo(new Date(), username, type, e.getMessage(), e.getStackTrace());
		// Insert the exception on the database
		try {
			exceptionInfoService.saveOne(excInfo);
		} catch (Exception e1) {
			logger.error(messageSource.getMessage("exception.insert_exception", null), e1);
			logger.error(e1.getMessage());
		}
		if (!excInfo.getType().equals(ExceptionType.APP_EXCEPTION)) {
			excInfo.setMessage(messageSource.getMessage("exception.message_exception_front", null));
		}
		excInfo.hideDetails();
		// Sent back to the front end
		ResponseEntity<ExceptionInfo> re = new ResponseEntity<>(excInfo, HttpStatus.INTERNAL_SERVER_ERROR);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(re).build();
	}
}
