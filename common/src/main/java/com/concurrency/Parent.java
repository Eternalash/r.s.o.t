package com.concurrency;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Author Bryan.C <br>
 * Date 2018/6/27 18:11
 */
public class Parent implements Runnable {
  BankCard bc = null;
  int type = 0;
  ReadWriteLock lock = null;
  Parent(BankCard bc, ReadWriteLock lock, int type) {
    this.bc = bc;
    this.lock = lock;
    this.type = type;
  }
  public void run() {
    try {
      while(true){
        lock.readLock().lock();
        if(type==2)
          System.out.println("父亲要查询，现在余额：" + bc.getBalance());
        else
          System.out.println("老妈要查询，现在余额：" + bc.getBalance());
        lock.readLock().unlock();
        Thread.sleep(1 * 1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
