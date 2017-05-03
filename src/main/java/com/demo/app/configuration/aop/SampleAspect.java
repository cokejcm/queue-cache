package com.demo.app.configuration.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {

	@AfterReturning(
	pointcut = "execution(* com.demo.app.controller.MessageController.messageById(..))",
	returning= "result")
	public void logExecution (JoinPoint joinPoint, Object result){
		System.out.println("Reading id: " + (String)result);
	}

}
