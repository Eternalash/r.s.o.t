package com.designpattern.decorator;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    private void method2() {
        System.out.println("method2 修饰");
    }

    @Override
    public void operate() {
        this.method2();
        super.operate();
    }
}
