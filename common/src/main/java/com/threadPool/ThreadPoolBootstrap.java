package com.threadPool;

import org.apache.tomcat.jni.Time;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author Bryan.C <br>
 * Date 2018/7/10 10:43
 */
public class ThreadPoolBootstrap {
  public static void main(String[] args) {
    Runnable myRunnable = () -> {
      try {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " run");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    };

    /**
     * 可以看到每个任务都是是直接启动一个核心线程来执行任务，一共创建了6个线程，不会放入队列中。8秒后线程池还是6个线程，核心线程默认情况下不会被回收，不收超时时间限制。
     */
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());//核心线程数为6，最大线程数为10。超时时间为5秒
    /**
     * 当任务数超过核心线程数时，会将超出的任务放在队列中，只会创建3个线程重复利用。
     */
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());//核心线程数为3，最大线程数为6。超时时间为5秒,队列是LinkedBlockingDeque
    /**
     * 当队列是SynchronousQueue时，超出核心线程的任务会创建新的线程来执行，看到一共有6个线程。
     * 但是这些线程是费核心线程，收超时时间限制，在任务完成后限制超过5秒就会被回收。所以最后看到线程池还是只有三个线程。
     */
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());//核心线程数为3，最大线程数为6。超时时间为5秒,队列是SynchronousQueue
    /**
     *LinkedBlockingDeque根本不受最大线程数影响。
     * 无界队列可以无限制接收任务直至队列溢出OOM
     */
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());//核心线程数是3，最大线程数是4，队列是LinkedBlockingDeque
    /**
     * 首先为三个任务开启了三个核心线程1，2，3，然后第四个任务和第五个任务加入到队列中，第六个任务因为队列满了，就直接创建一个新线程4，这是一共有四个线程，没有超过最大线程数。
     * 8秒后，非核心线程收超时时间影响回收了，因此线程池只剩3个线程了。
     */
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2));
    /**
     * 直接出错在第6个execute方法上。因为核心线程是3个，当加入第四个任务的时候，就把第四个放在队列中。
     * 加入第五个任务时，因为队列满了，就创建新线程执行，创建了线程4。当加入第六个线程时，也会尝试创建线程，但是因为已经达到了线程池最大线程数，所以直接抛异常了。
     */
//    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1));

    /**
     * 这次在添加第五个任务时就报错了，因为SynchronousQueue根本不保存任务，收到一个任务就去创建新线程。所以第五个就会抛异常了。
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());//核心线程数是3 ，最大线程数是4，队列是SynchronousQueue

    executor.execute(myRunnable);
    executor.execute(myRunnable);
    executor.execute(myRunnable);
    System.out.println("---先开三个---");
    System.out.println("核心线程数" + executor.getCorePoolSize());
    System.out.println("线程池数" + executor.getPoolSize());
    System.out.println("队列任务数" + executor.getQueue().size());
    executor.execute(myRunnable);
    executor.execute(myRunnable);
    executor.execute(myRunnable);
    System.out.println("---再开三个---");
    System.out.println("核心线程数" + executor.getCorePoolSize());
    System.out.println("线程池数" + executor.getPoolSize());
    System.out.println("队列任务数" + executor.getQueue().size());
    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("----8秒之后----");
    System.out.println("核心线程数" + executor.getCorePoolSize());
    System.out.println("线程池数" + executor.getPoolSize());
    System.out.println("队列任务数" + executor.getQueue().size());

    executor.shutdown();
  }

}
