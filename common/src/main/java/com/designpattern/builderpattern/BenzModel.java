package com.designpattern.builderpattern;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 */
public class BenzModel extends CardModel {
    @Override
    void start() {
        System.out.println("benz start");
    }

    @Override
    void stop() {
        System.out.println("benz stop");
    }

    @Override
    void alarm() {
        System.out.println("benz alarm");
    }

    @Override
    void engineBoom() {
        System.out.println("benz engineBoom");
    }
}
