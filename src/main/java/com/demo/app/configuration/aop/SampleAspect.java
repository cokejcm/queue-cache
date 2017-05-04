package com.demo.app.configuration.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.demo.app.domain.Entity;

@Aspect
@Component
public class SampleAspect {

	@AfterReturning(pointcut = "execution(* com.demo.app.controller.*.*(..))", returning= "result")
	public void logExecution (JoinPoint joinPoint, Object result){
		System.out.println("Reading id: " + ((Entity)result).getId());
	}

}
