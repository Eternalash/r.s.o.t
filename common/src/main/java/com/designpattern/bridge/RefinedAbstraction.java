package com.designpattern.bridge;

public class RefinedAbstraction extends Abstraction {

    //覆写构造函数
    public RefinedAbstraction(Implementor imp) {
        super(imp);
    }

    //修正父类行为
    @Override
    public void request() {
        super.request();
        super.getImp().doAnything();
    }

}
