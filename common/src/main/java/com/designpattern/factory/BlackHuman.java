package com.designpattern.factory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("black");
    }

    @Override
    public void talk() {
        System.out.println("balabala");
    }
}
