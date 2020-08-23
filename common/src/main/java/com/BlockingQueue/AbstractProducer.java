package com.BlockingQueue;

/**
 * Created by Bryan.C on 2017/12/5.
 */
abstract class AbstractProducer implements Producer, Runnable {
  @Override
  public void run() {
    while (true) {
      try {
        produce();
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
