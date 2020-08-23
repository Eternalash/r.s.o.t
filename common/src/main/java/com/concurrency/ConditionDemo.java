package com.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author Bryan.C <br>
 * Date 2018/7/17 11:04
 */
public class ConditionDemo {
  public static void main(String[] args) {

    ExecutorService executorService = Executors.newFixedThreadPool(1, r -> {
      Thread thread = new Thread(r);
      thread.setDaemon(true);
      return thread;
    });
    BankCard bankCard = new BankCard();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * new ReentrantLock(true)公平锁，son与father获取到锁的顺序不固定
     */
    ReentrantLock reentrantLock = new ReentrantLock();

    Condition notEmpty = reentrantLock.newCondition();
    Condition notFull = reentrantLock.newCondition();

    CompletableFuture<Void> kid = CompletableFuture.runAsync(() -> {
      try {
        while (true) {
          // lock.writeLock().lock();
          reentrantLock.lock();
          System.out.println("kid" + reentrantLock.getHoldCount());
          System.out.println("龟儿子要消费，现在余额：" + bankCard.getBalance());
          if (bankCard.getBalance() <= 4000) {
            System.out.println("余额不足");
            notEmpty.await();
          }
          bankCard.setBalance(bankCard.getBalance() - 4000);
          System.out.println("龟儿子消费4000元，现在余额：" + bankCard.getBalance());
          // lock.writeLock().unlock();
          notFull.signalAll();
          /**
           * 当前线程依然能获取到锁，不需要参与竞争，state+1
           */
          // reentrantLock.lock();
          Thread.sleep(1 * 1000);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        reentrantLock.unlock();
      }
    }, executorService);

    CompletableFuture<Void> father = CompletableFuture.runAsync(() -> {
      try {
        while (true) {
          // lock.readLock().lock();
          reentrantLock.lock();
          /**
           * 当具有时限的活 动调用了阻塞方法，定时锁能够在时间预算内设定相应的超时。如果活动在期待的时间内没能获得结果，定时锁能使程序提前返回。可定时的锁获取模式，由tryLock(long, TimeUnit)方法实现
           */
          // reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);

          System.out.println("父亲" + reentrantLock.getHoldCount());
          Thread.sleep(1 * 1000);
          if (bankCard.getBalance() < 4000) {
            bankCard.setBalance(bankCard.getBalance() + 5000);
            System.out.println("父亲看余额不多加5000，现在余额：" + bankCard.getBalance());
            notEmpty.signalAll();
          } else {
            notFull.await();
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        // lock.readLock().unlock();
        reentrantLock.unlock();
      }
    });

    CompletableFuture<Void> mother = CompletableFuture.runAsync(() -> {
      try {
        while (true) {
          // lock.readLock().lock();
          reentrantLock.lock();
          /**
           * 当具有时限的活 动调用了阻塞方法，定时锁能够在时间预算内设定相应的超时。如果活动在期待的时间内没能获得结果，定时锁能使程序提前返回。可定时的锁获取模式，由tryLock(long, TimeUnit)方法实现
           */
          // reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);

          System.out.println("母亲" + reentrantLock.getHoldCount());
          Thread.sleep(1 * 1000);
          if (bankCard.getBalance() < 8000) {
            bankCard.setBalance(bankCard.getBalance() + 2000);
            System.out.println("母亲看余额不多加3000，现在余额：" + bankCard.getBalance());
            notEmpty.signalAll();
          } else {
            notFull.await();
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        // lock.readLock().unlock();
        reentrantLock.unlock();
      }
    });

    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    kid.complete(null);
    mother.complete(null);
    father.complete(null);

    // ExecutorService pool = Executors.newCachedThreadPool();
    // Consumer cm1 = new Consumer(bankCard, lock);
    // Parent cm2 = new Parent(bankCard, lock , 1);
    // pool.execute(cm1);
    // pool.execute(cm2);

  }

}
