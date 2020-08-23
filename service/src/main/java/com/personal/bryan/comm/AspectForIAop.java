package com.personal.bryan.comm;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Author: Bryan.C <br>
 * Date: 2019-07-05 23:06
 * @description:
 */
@Aspect
@Component
public class AspectForIAop {
	@Around(value = "@annotation(com.personal.bryan.comm.AopTest)")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		proceedingJoinPoint.getArgs()[0]="Hello "+proceedingJoinPoint.getArgs()[0];
		proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
	}
}
