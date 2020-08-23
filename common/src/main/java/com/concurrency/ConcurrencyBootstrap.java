package com.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author Bryan.C <br>
 */
public class ConcurrencyBootstrap {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(1, r -> {
//            Thread thread = new Thread(r);
//            thread.setDaemon(true);
//            return thread;
//        });
        UnsafeSequence unsafeSequence1 = new UnsafeSequence();
//        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            completableFutures.add(CompletableFuture.runAsync(() -> {
//                for (int j = 0; j < 1000; j++) {
//                    unsafeSequence1.getNext();
//                }
//            },executorService));
//        }
//        completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
//
//        System.out.println(unsafeSequence1.getValue());

        Runnable runnable=()->{
            for (int j = 0; j < 1000; j++) {
                    unsafeSequence1.getNext();
                }
        };
        Executor executor=new ThreadPoolExecutor(5,10,500,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
        ((ThreadPoolExecutor) executor).shutdown();
        while (!((ThreadPoolExecutor) executor).isTerminated()) {
        }

        System.out.println(unsafeSequence1.getValue());

    }
}
