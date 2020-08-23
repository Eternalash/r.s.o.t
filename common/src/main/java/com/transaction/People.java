package com.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bryan.C on 2017/4/25.
 */
@Component
@Setter
@Getter
public class People {
  private int age;
  private String name;
  ReentrantLock lock = new ReentrantLock();

  int count = 0;

  synchronized void increment() {
      count = count + 1;
  }
}
