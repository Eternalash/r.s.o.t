package com.personal.bryan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 10:40
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.personal.bryan.comm", "com.personal.bryan.spring","com.personal.bryan.abstractservice","com.personal.bryan.generator"})
//@ComponentScan(basePackages = {"com.personal.bryan.circulardependencies"})
@EnableAspectJAutoProxy
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
