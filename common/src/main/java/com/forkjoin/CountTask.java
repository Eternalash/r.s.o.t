package com.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Author Bryan.C <br>
 * Date 2018/7/11 10:33
 */
public class CountTask extends RecursiveTask {
  private static final int THRESHOLD=2;
  private int start;
  private int end;

  public CountTask(int start,int end){
    this.start=start;
    this.end=end;
  }

  @Override
  protected Integer compute() {
    int sum=0;
    boolean canCompute=(end-start)<=THRESHOLD;
    if(canCompute){
      for(int i=start;i<=end;i++){
        sum+=i;
      }
    }else{
      int middle=(start+end)/2;
      CountTask leftTask=new CountTask(start,middle);
      CountTask rightTask=new CountTask(middle+1,end);
      leftTask.fork();
      rightTask.fork();

      int leftResult= (int) leftTask.join();
      int rightResult= (int) rightTask.join();
       sum=leftResult+rightResult;
    }
    return sum;
  }
}
