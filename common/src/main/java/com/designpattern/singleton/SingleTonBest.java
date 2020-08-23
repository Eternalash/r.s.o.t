package com.designpattern.singleton;

import org.junit.runner.notification.RunListener;


@RunListener.ThreadSafe
public class SingleTonBest {
    //私有构造方法
    private SingleTonBest(){

    }

    //静态工厂方法
    public static SingleTonBest getInstance(){
        return SingleTon.INSTANCE.getSingleTon();
    }

    private enum SingleTon{
        INSTANCE;

        private SingleTonBest singleTon;

        //JVM保证这个方法只调用一次
        SingleTon(){
            singleTon = new SingleTonBest();
        }

        public SingleTonBest getSingleTon(){
            return singleTon;
        }
    }
}
