package com.designpattern.decorator;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public class ConcreteComponent implements Component {
    @Override
    public void operate() {
        System.out.println("do something");
    }
}
