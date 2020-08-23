package com.designpattern.abstractfactory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public abstract class AbstractBlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("black");
    }

    @Override
    public void talk() {
        System.out.println("balabala");
    }
}
