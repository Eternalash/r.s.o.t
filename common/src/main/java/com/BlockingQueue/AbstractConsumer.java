package com.BlockingQueue;

/**
 * Created by Bryan.C on 2017/12/5.
 */
abstract class AbstractConsumer implements Consumer, Runnable {

  @Override
  public void run() {
    while (true) {
      try {
        consume();
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
