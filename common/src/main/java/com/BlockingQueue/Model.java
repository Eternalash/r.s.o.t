package com.BlockingQueue;

/**
 * Created by Bryan.C on 2017/12/5.
 */
public interface Model {
  Runnable newRunnableConsumer();
  Runnable newRunnableProducer();
}
