package com.concurrency;

import java.util.concurrent.*;

public class VolatileBootstrap {
    public static void main(String[] args){
        StoppableTask task=new StoppableTask();
        StoppableTask task1=new StoppableTask();
        StoppableTask task2=new StoppableTask();
        ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
                .setNamePrefix("DemoPool-Thread").setDaemon(false)
                .setPriority(Thread.MAX_PRIORITY).build();
        Executor executor=new ThreadPoolExecutor(5,10,500, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(),customThreadfactory);
        executor.execute(task);
        executor.execute(task1);
        executor.execute(task2);

        ((ThreadPoolExecutor) executor).shutdown();
        while (!((ThreadPoolExecutor) executor).isTerminated()) {
        }
    }
}
