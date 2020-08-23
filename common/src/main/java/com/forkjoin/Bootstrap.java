package com.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Author Bryan.C <br>
 * Date 2018/7/11 10:39
 */
public class Bootstrap {
  public static void main(String[] args){
    ForkJoinPool forkJoinPool=new ForkJoinPool();
    CountTask countTask=new CountTask(1,4);
    Future future=forkJoinPool.submit(countTask);
    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}
