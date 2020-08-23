package com.designpattern.adapter;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public class ClassAdapter extends Adaptee implements Target{
    @Override
    public void request() {
        super.doSomeThing();
    }
}
