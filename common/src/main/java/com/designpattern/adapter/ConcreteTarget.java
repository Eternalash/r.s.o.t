package com.designpattern.adapter;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("request");
    }
}
