package com.designpattern.abstractfactory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class MaleAbstractBlackHuman extends AbstractBlackHuman {
    @Override
    public void getSex() {
        System.out.println("Male");
    }
}
