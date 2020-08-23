package com.designpattern.abstractfactory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public abstract class AbstractYellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("yellow");
    }

    @Override
    public void talk() {
        System.out.println("你好");
    }
}
