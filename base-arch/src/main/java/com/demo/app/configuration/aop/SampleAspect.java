package com.demo.app.configuration.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Entity;

@Profile("dev")
@Aspect
@Component
public class SampleAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterReturning(pointcut = "execution(* com.demo.app.controller.*.*(..))", returning = "result")
	public void logExecution(JoinPoint joinPoint, Object result) {
		if (result instanceof Entity) {
			logger.info("Reading id: " + ((Entity) result).getId());
		}

	}
}
