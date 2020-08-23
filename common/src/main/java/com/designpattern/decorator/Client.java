package com.designpattern.decorator;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public class Client {
    public static void main(String[] args){
        Component component=new ConcreteComponent();
        component=new ConcreteDecorator1(component);
        component=new ConcreteDecorator2(component);
        component.operate();
    }
}
