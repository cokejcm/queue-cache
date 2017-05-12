package com.demo.app.configuration.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Entity;

@Aspect
@Component
public class SampleAspect {

	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(pointcut = "execution(* com.demo.app.controller.*.*(..))", returning= "result")
	public void logExecution (JoinPoint joinPoint, Object result){
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		if (result instanceof Entity){
			logger.info("Reading id: " + ((Entity)result).getId());
		}

	}
}
