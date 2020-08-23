package com.designpattern.bridge;

public abstract class Abstraction {
    private Implementor imp;

    //约束子类必须实现该构造函数
    public Abstraction(Implementor imp) {
        this.imp = imp;
    }

    public Implementor getImp() {
        return imp;
    }

    //自身的行为和属性
    public void request() {
        this.imp.doSomething();
    }
}
