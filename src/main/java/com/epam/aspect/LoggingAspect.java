package com.epam.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {


	@Before("execution(* com.epam.dao.CustomerDao.getAll())")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("logBefore() is running");
		System.out.println("hijacket : " + joinPoint.getSignature().getName());
		System.out.println("********");
	}

	@Pointcut("execution(* com.epam.dao.CustomerDao.insert())")
	private void businessService() {
		System.out.println("Insert method");
	}
}
