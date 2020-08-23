package com.concurrency;

public class StoppableTask  extends Thread {
    private volatile boolean pleaseStop;


    @Override
    public void run() {
        while (!pleaseStop) {
            System.out.print("Simple task is running on " + Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority()+ "Thread stopped \n");
        }
    }

    public void tellMeToStop() {
        pleaseStop = true;
    }
}
