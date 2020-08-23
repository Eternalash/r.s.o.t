package com.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


/**
 * Created by Bryan.C on 2017/1/5.
 */
@Aspect
public class SleepHelper {

  @Pointcut("execution(* com.springaop.PersonService.*(..))")
  private void anyMethod() {
  }

  @Before("anyMethod() && args(start,time)")
  public void beforesleep(int start, int time) {
    System.out.println("睡觉前要喝杯牛奶");
  }

  @AfterReturning(pointcut = "anyMethod() && args(start,time)", returning = "result", argNames = "start,time,result")
  /**
   * 当使用around和afterreturning时,around必须有返回
   */
  public void afterreturn(int start, int time, int result) {
    System.out.println("从" + start + "点，睡到"+time+"点,睡了" + result + "小时");
  }


  @AfterThrowing(pointcut = "anyMethod()", throwing = "e")
  public void afterthrow(Exception e) {
    System.out.println(e.toString());
  }

  @After("anyMethod()")
  public void aftersleep() {
    System.out.println("睡醒了好精神");
  }

  @Around("anyMethod() && args(start,time)")
  public Object aroundsleep(ProceedingJoinPoint jp, int start, int time) throws Throwable {
    Object o = jp.proceed();//将返回值赋予o再return
    System.out.println("睡觉zzzzz");
    Thread.sleep(time * 1000);
    return o;
  }

}
