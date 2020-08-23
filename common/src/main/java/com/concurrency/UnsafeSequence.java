package com.concurrency;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnsafeSequence {
//    private volatile int value; //不能保证线程安全
    private int value;

    public synchronized int getNext(){
        return value++;
    }
}
