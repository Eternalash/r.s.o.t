package com.transaction;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.util.concurrent.*;


/**
 * Created by Bryan.C on 2017/1/9.
 */
public class Main {
  /**
   * 程序入口. main 函数
   *
   * @param args agrs
   * @throws IOException IOException
   */
  static final int MAXIMUM_CAPACITY = 1 << 30;

  public static void main(String[] args) {

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(100, 200, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    People people=new People();
    for (int i = 0; i < 1000; i++) {
      singleThreadPool.execute(people::increment);
    }
    ((ThreadPoolExecutor) singleThreadPool).prestartAllCoreThreads();
    People people1=new People();
    for(int i=0;i<1000;i++){
      Thread thread=new Thread(people1::increment);
      thread.start();
    }
    System.out.println("people-"+ people.count);
      System.out.println("people1-"+ people1.count);

  }


  static int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

  public double get() {
    return 0.02;
  }

}

