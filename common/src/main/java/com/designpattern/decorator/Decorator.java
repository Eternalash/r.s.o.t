package com.designpattern.decorator;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public abstract class Decorator implements Component {
    private Component component = null;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operate() {
        this.component.operate();
    }
}
