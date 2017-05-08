package com.demo.app.configuration.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Entity;

@Aspect
@Component
public class SampleAspect {

	private static final Logger LOGGER = LogManager.getLogger(SampleAspect.class);

	@AfterReturning(pointcut = "execution(* com.demo.app.controller.*.*(..))", returning= "result")
	public void logExecution (JoinPoint joinPoint, Object result){
		if (result instanceof Entity){
			LOGGER.info("Reading id: " + ((Entity)result).getId());
			LOGGER.debug("This is a debug message");

			LOGGER.info("This is an info message");

			LOGGER.warn("This is a warn message");

			LOGGER.error("This is an error message");

			LOGGER.fatal("This is a fatal message");
		}
	}
}
